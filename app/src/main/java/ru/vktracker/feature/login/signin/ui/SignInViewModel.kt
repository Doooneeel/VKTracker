package ru.vktracker.feature.login.signin.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vktracker.core.ui.BaseViewModel
import ru.vktracker.core.ui.view.fab.FabViewState
import ru.vktracker.core.ui.view.state.ViewState
import ru.vktracker.feature.login.signin.domain.SighInInteractor
import ru.vktracker.feature.login.signin.ui.validate.SignInValidateInput
import javax.inject.Inject

/**
 * @author Danil Glazkov on 22.06.2023, 16:33
 */
interface SignInViewModel : BaseViewModel, SignInNavigation.External, SignInCommunications.Observe {

    fun changeInput(login: String, password: CharArray)

    fun login(login: String, password: CharArray)


    @HiltViewModel
    class Base @Inject constructor(
        private val interactor: SighInInteractor,
        private val communications: SignInCommunications.Mutable,
        private val handleDomainRequest: SingInHandleDomainRequest,
        private val validateInput: SignInValidateInput,
        private val navigation: SignInNavigation.Combine,
    ) : ViewModel(),
        SignInViewModel,
        SignInNavigation.External by navigation,
        SignInCommunications.Observe by communications
    {
        override fun login(login: String, password: CharArray) {
            communications.putInputViewState(ViewState.DISABLE)
            communications.putFabViewState(FabViewState.INVISIBLE)
            communications.putProgressViewState(ViewState.VISIBLE)

            handleDomainRequest.handle(viewModelScope) {
                interactor.login(login, password)
            }
        }

        override fun changeInput(login: String, password: CharArray) {
            communications.putFabViewState(
                FabViewState.Base(validateInput.isValid(login, password))
            )
        }
    }
}