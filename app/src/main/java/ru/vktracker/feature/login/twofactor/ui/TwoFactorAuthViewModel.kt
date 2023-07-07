package ru.vktracker.feature.login.twofactor.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vktracker.R
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.ui.viewmodel.BaseViewModel
import ru.vktracker.core.ui.resources.ManageResources
import ru.vktracker.core.ui.view.fab.FabViewState
import ru.vktracker.core.ui.view.progress.ProgressViewState
import ru.vktracker.core.ui.view.state.ViewState
import ru.vktracker.feature.login.twofactor.domain.TwoFactorDomainResponse
import ru.vktracker.feature.login.twofactor.domain.TwoFactorAuthInteractor
import ru.vktracker.feature.login.twofactor.ui.dialog.TwoFactorFailureDialog
import javax.inject.Inject

/**
 * @author Danil Glazkov on 25.06.2023, 18:02
 */
interface TwoFactorAuthViewModel : BaseViewModel,
    TwoFactorNavigation.Fragment,
    TwoFactorCommunications.Observe
{
    fun changeInputCode(code: String, isComplete: Boolean)

    fun confirmCode(code: String)

    fun resendCode()


    @HiltViewModel
    class Base @Inject constructor(
        private val interactor: TwoFactorAuthInteractor,
        private val communications: TwoFactorCommunications.Mutable,
        private val navigation: TwoFactorNavigation.Combine,
        private val resendCodeTimer: ResendCodeTimer,
        private val handleError: TwoFactorHandleError,
        private val dispatchers: CoroutineDispatchers,
        private val resources: ManageResources,
        private val args: TwoFactorAuthArgs,
    ) : BaseViewModel.Abstract(dispatchers),
        TwoFactorAuthViewModel,
        TwoFactorNavigation.Fragment by navigation,
        TwoFactorCommunications.Observe by communications
    {
        private val responseMapper = object : TwoFactorDomainResponse.Mapper<Unit> {
            override fun failure(exception: Exception) {
                communications.putConfirmCodeLayoutViewState(ViewState.ENABLE)
                communications.putProgressViewState(ProgressViewState.HIDE)

                val handleShowing = {
                    communications.putFabViewState(FabViewState.INVISIBLE)
                }
                val handleCancel = {
                    communications.putFabViewState(FabViewState.VISIBLE)
                }

                communications.putDialog(
                    TwoFactorFailureDialog(
                        handleError.handle(exception),
                        handleShowing,
                        handleCancel
                    )
                )
            }

            override fun success(token: String) {
                communications.putConfirmCodeLayoutViewState(ViewState.ENABLE)
                communications.putFabViewState(FabViewState.VISIBLE)
                communications.putProgressViewState(ProgressViewState.HIDE)

                communications.putProgressViewState(ProgressViewState.HIDE)
                navigation.navigateToTabsFragment(token)
            }
        }

        override fun changeInputCode(code: String, isComplete: Boolean) {
            communications.putFabViewState(
                FabViewState.Base(isComplete && !interactor.codeHasBeenSent(code))
            )
        }

        override fun init(isFistRun: Boolean) {
            if (isFistRun) {
                handle { interactor.requestCode(args.redirectUrl()) }

                resendCodeTimer.start()
                communications.putAboutSendingMessage(
                    resources.string(R.string.code_send, args.phoneMask())
                )
            }
        }

        override fun resendCode() { resendCodeTimer.start() }

        override fun confirmCode(code: String) {
            communications.putProgressViewState(ProgressViewState.SHOW)
            communications.putFabViewState(FabViewState.INVISIBLE)

            dispatchers.io(viewModelScope) {
                val response: TwoFactorDomainResponse = interactor.confirmCode(code)

                dispatchers.changeToUi {
                    response.map(responseMapper)
                }
            }
        }
    }
}