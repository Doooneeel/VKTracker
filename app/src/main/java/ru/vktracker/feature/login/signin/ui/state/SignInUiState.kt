package ru.vktracker.feature.login.signin.ui.state

import kotlinx.parcelize.Parcelize
import ru.vktracker.R
import ru.vktracker.core.ui.state.*
import ru.vktracker.core.ui.state.UiState.*
import ru.vktracker.databinding.FragmentSignInBinding
import ru.vktracker.feature.login.signin.ui.dialogs.SignInFailDialog

/**
 * @author Danil Glazkov on 09.07.2023, 17:28
 */
interface SignInUiState : UiState<FragmentSignInBinding> {

    @Parcelize
    object Nothing : AbstractNothing<FragmentSignInBinding>(), SignInUiState


    abstract class Abstract(
        private val progress: ProgressUiState,
        private val fab: FabUiState,
        private val navigationIcon: ToolbarUiState,
        private val inputViews: ViewUiState,
    ) : SignInUiState {
        override fun update(view: FragmentSignInBinding) = view.run {
            progress.update(progressIndicator)
            fab.update(loginFloatingActionButton)
            navigationIcon.update(toolBar)
            inputViews.update(loginTextInputLayout)
            inputViews.update(passwordTextInputLayout)
        }
    }


    @Parcelize
    class Login : Abstract(
        ProgressUiState.Show, FabUiState.Hide, ToolbarUiState.HideNavIcon, ViewUiState.Disable
    )


    @Parcelize
    object ValidInput : SignInUiState {
        override fun update(view: FragmentSignInBinding) = FabUiState.Show.update(
            view.loginFloatingActionButton
        )
    }


    @Parcelize
    object InvalidInput : SignInUiState {
        override fun update(view: FragmentSignInBinding) = FabUiState.Hide.update(
            view.loginFloatingActionButton
        )
    }

    @Parcelize
    class FailDialog(
        private val message: String,
        private val dialogIsCancel: DialogCancelState = DialogCancelState.Base(),
    ) : AbstractDialog<FragmentSignInBinding>(
        OpeningDialog(), ClosingDialog(), dialogIsCancel
    ) , SignInUiState {
        override fun showDialog(view: FragmentSignInBinding) {
            SignInFailDialog(message) { dialogCanceled(view) }.show(view.root)
        }
    }

    @Parcelize
    private class ClosingDialog : Abstract(
        ProgressUiState.Hide, FabUiState.Show, ToolbarUiState.ShowNavIcon, ViewUiState.Enable
    ) {
        override fun update(view: FragmentSignInBinding) {
            view.loginFloatingActionButton.setText(R.string.to_retry)
            super.update(view)
        }
    }

    @Parcelize
    private class OpeningDialog : Abstract(
        ProgressUiState.Hide, FabUiState.Hide, ToolbarUiState.HideNavIcon, ViewUiState.Disable
    )

}