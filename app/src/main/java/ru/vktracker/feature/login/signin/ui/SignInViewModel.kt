package ru.vktracker.feature.login.signin.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.ui.viewmodel.BaseViewModel
import ru.vktracker.feature.login.signin.domain.SighInInteractor
import ru.vktracker.feature.login.signin.domain.SignInDomainException
import ru.vktracker.feature.login.signin.domain.SignInDomainResult
import ru.vktracker.feature.login.signin.ui.state.SignInUiState
import ru.vktracker.feature.login.signin.ui.validate.SignInValidateInput
import javax.inject.Inject

/**
 * @author Danil Glazkov on 22.06.2023, 16:33
 */
interface SignInViewModel : BaseViewModel, SignInNavigation.External, SignInCommunications.Observe,
    SignInActions {


    @HiltViewModel
    class Base @Inject constructor (
        private val interactor: SighInInteractor,
        private val communications: SignInCommunications.Mutable,
        private val validateInput: SignInValidateInput,
        private val handleError: SignInHandleError,
        private val navigation: SignInNavigation.Combined,
        private val dispatchers: CoroutineDispatchers,
    ) : ViewModel(),
        SignInViewModel,
        SignInNavigation.External by navigation,
        SignInCommunications.Observe by communications
    {
        private val handleResponseMapper = object : SignInDomainResult.Mapper<Unit> {
            override fun success(token: CharArray) {
                communications.put(SignInUiState.Nothing)
                navigation.navigateToTabsScreen()
            }

            override fun failure(exception: SignInDomainException) = communications.put(
                SignInUiState.FailDialog(message = handleError.handle(exception))
            )

            override fun mapTwoFactorAuth(phoneMask: String, redirectUrl: String) {
                communications.put(SignInUiState.Nothing)
                navigation.navigateToTwoFactorScreen(phoneMask, redirectUrl)
            }
        }

        override fun login(login: String, password: CharArray) {
            communications.put(SignInUiState.Login())

            dispatchers.io(viewModelScope) {
                val response: SignInDomainResult = interactor.login(login, password)

                dispatchers.changeToUi {
                    response.map(handleResponseMapper)
                }
            }
        }

        override fun changeInput(login: String, password: CharArray) = communications.put(
            if (validateInput.isValid(login, password))
                SignInUiState.ValidInput
            else
                SignInUiState.InvalidInput
        )
    }
}