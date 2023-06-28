package ru.vktracker.feature.login.signin.ui

import ru.vktracker.R
import ru.vktracker.core.ui.HandleError
import ru.vktracker.core.ui.ProgressCommunication
import ru.vktracker.core.ui.dialog.DialogCommunication
import ru.vktracker.core.ui.resources.ProvideString
import ru.vktracker.core.ui.view.fab.FabViewState
import ru.vktracker.core.ui.view.fab.FabViewStateCommunication
import ru.vktracker.core.ui.view.state.ViewState
import ru.vktracker.core.ui.view.state.ViewStateCommunication
import ru.vktracker.feature.login.signin.domain.SignInDomainException.*
import ru.vktracker.feature.login.signin.ui.dialogs.FailedLoginDialog
import java.lang.Exception

/**
 * @author Danil Glazkov on 27.06.2023, 22:51
 */
interface SignInHandleError : HandleError.Unit {

    class Base(
        private val dialogCommunication: DialogCommunication,
        private val fabViewStateCommunication: FabViewStateCommunication,
        private val loginInputViewStateCommunication: ViewStateCommunication,
        private val progressCommunication: ProgressCommunication,
        private val resources: ProvideString.Single
    ) : SignInHandleError {

        override fun handle(exception: Exception) {
            progressCommunication.put(ViewState.INVISIBLE)

            val message: String = resources.string(
                when (exception) {
                    is IncorrectLoginData -> R.string.incorrect_login_or_password
                    is NoInternetConnectionException -> R.string.internet_connection_error
                    else -> R.string.unknown_error
                }
            )

            dialogCommunication.put(
                FailedLoginDialog(message) {
                    fabViewStateCommunication.put(FabViewState.VISIBLE)
                    loginInputViewStateCommunication.put(ViewState.ENABLE)
                }
            )
        }
    }
}