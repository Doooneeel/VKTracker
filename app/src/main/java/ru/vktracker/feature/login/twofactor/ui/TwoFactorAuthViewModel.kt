package ru.vktracker.feature.login.twofactor.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.ui.viewmodel.BaseViewModel
import ru.vktracker.feature.login.twofactor.domain.*
import ru.vktracker.feature.login.twofactor.ui.state.TwoFactorUiState
import javax.inject.Inject

/**
 * @author Danil Glazkov on 25.06.2023, 18:02
 */
interface TwoFactorAuthViewModel : BaseViewModel, TwoFactorNavigation.External, TwoFactorActions,
    TwoFactorCommunications.Observe {


    @HiltViewModel
    class Base @Inject constructor(
        private val interactor: TwoFactorAuthInteractor,
        private val communications: TwoFactorCommunications.Mutable,
        private val navigation: TwoFactorNavigation.Combined,
        private val handleError: TwoFactorHandleError,
        private val dispatchers: CoroutineDispatchers,
        private val args: TwoFactorAuthArgs,
    ) : BaseViewModel.Abstract(dispatchers),
        TwoFactorAuthViewModel,
        TwoFactorNavigation.External by navigation,
        TwoFactorCommunications.Observe by communications
    {
        private val responseMapper = object : TwoFactorDomainResult.Mapper<Unit> {
            override fun success(token: String) {
                communications.put(TwoFactorUiState.Nothing)
                navigation.navigateToTabsFragment(token)
            }

            override fun failure(exception: TwoFactorException) = communications.put(
                TwoFactorUiState.FailDialog(
                    handleError.handle(exception)
                )
            )
        }

        override fun init(isFistRun: Boolean) {
            if (isFistRun) {
                handle { interactor.requestCode(args.redirectUrl()) }

                communications.put(
                    TwoFactorUiState.ChangePhoneMask(args.phoneMask())
                )
            }
        }

        override fun resendCode() { /* todo resend */}

        override fun confirmCode(code: String) {
            communications.put(TwoFactorUiState.ConfirmCode())

            dispatchers.io(viewModelScope) {
                val response: TwoFactorDomainResult = interactor.confirmCode(code)

                dispatchers.changeToUi {
                    response.map(responseMapper)
                }
            }
        }

        override fun changeConfirmationCodeState(isComplete: Boolean) = communications.put(
            if (isComplete)
                TwoFactorUiState.CodeEntryCompleted
            else
                TwoFactorUiState.CodeEntryNotCompleted
        )
    }
}