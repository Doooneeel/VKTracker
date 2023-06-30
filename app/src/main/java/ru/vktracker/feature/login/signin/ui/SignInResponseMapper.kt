package ru.vktracker.feature.login.signin.ui

import ru.vktracker.core.ui.dialog.DialogCommunication
import ru.vktracker.core.ui.view.fab.FabViewState
import ru.vktracker.core.ui.view.fab.FabViewStateCommunication
import ru.vktracker.core.ui.view.progress.ProgressCommunication
import ru.vktracker.core.ui.view.progress.ProgressViewState
import ru.vktracker.core.ui.view.state.ViewState
import ru.vktracker.core.ui.view.state.ViewStateCommunication
import ru.vktracker.feature.login.signin.domain.SignInDomainResponse
import ru.vktracker.feature.login.signin.ui.dialogs.FailedLoginDialog
import java.lang.Exception

/**
 * @author Danil Glazkov on 25.06.2023, 14:02
 */
interface SignInResponseMapper : SignInDomainResponse.Mapper<Unit> {

    class Base(
        private val dialogCommunication: DialogCommunication,
        private val fabViewStateCommunication: FabViewStateCommunication,
        private val loginInputViewStateCommunication: ViewStateCommunication,
        private val progressCommunication: ProgressCommunication,
        private val navigation: SignInNavigation.Internal,
        private val handleError: SignInHandleError
    ) : SignInResponseMapper {

        override fun success(token: CharArray) = navigation.navigateToTabsScreen()

        override fun failure(exception: Exception) {
            progressCommunication.put(ProgressViewState.HIDE)

            val message: String = handleError.handle(exception)
            val handleShowing = { fabViewStateCommunication.put(FabViewState.INVISIBLE) }
            val handleClosing = {
                fabViewStateCommunication.put(FabViewState.VISIBLE)
                loginInputViewStateCommunication.put(ViewState.ENABLE)
            }
            dialogCommunication.put(
                FailedLoginDialog(message, handleShowing, handleClosing)
            )
        }

        override fun mapTwoFactorAuth(phoneMask: String, redirectUrl: String) {
            progressCommunication.put(ProgressViewState.HIDE)

            navigation.navigateToTwoFactorScreen(phoneMask, redirectUrl)
        }
    }
}