package ru.vktracker.feature.login.twofactor.ui.state

import kotlinx.parcelize.Parcelize
import ru.vktracker.R
import ru.vktracker.core.ui.state.*
import ru.vktracker.core.ui.state.UiState.*
import ru.vktracker.databinding.FragmentTwoFactorAuthBinding
import ru.vktracker.feature.login.twofactor.ui.dialog.TwoFactorFailureDialog

/**
 * @author Danil Glazkov on 10.07.2023, 15:09
 */
interface TwoFactorUiState : UiState<FragmentTwoFactorAuthBinding> {

    @Parcelize
    object Nothing : AbstractNothing<FragmentTwoFactorAuthBinding>(), TwoFactorUiState


    abstract class Abstract(
        private val progress: ProgressUiState,
        private val fab: FabUiState,
        private val navigationIcon: ToolbarUiState,
        private val confirmationCode: ViewUiState,
    ) : TwoFactorUiState {
        override fun update(view: FragmentTwoFactorAuthBinding) = view.run {
            progress.update(progressIndicator)
            fab.update(confirmFloatingActionButton)
            navigationIcon.update(toolBar)
            confirmationCode.update(view.confirmCodeView)
        }
    }


    @Parcelize
    class ConfirmCode : Abstract(
        ProgressUiState.Show, FabUiState.Hide, ToolbarUiState.HideNavIcon, ViewUiState.Disable
    )


    @Parcelize
    object CodeEntryCompleted : Abstract(
        ProgressUiState.Hide, FabUiState.Show, ToolbarUiState.ShowNavIcon, ViewUiState.Enable
    )


    @Parcelize
    object CodeEntryNotCompleted : Abstract(
        ProgressUiState.Hide, FabUiState.Hide, ToolbarUiState.ShowNavIcon, ViewUiState.Enable
    )


    @Parcelize
    class ChangePhoneMask(private val phoneMask: String) : TwoFactorUiState {
        override fun update(view: FragmentTwoFactorAuthBinding) = view.run {
            CodeEntryNotCompleted.update(view)
            descriptionTextView.text = root.resources.getString(R.string.code_send, phoneMask)
        }
    }


    @Parcelize
    class FailDialog(
        private val message: String,
        private val dialogIsCancel: DialogCancelState = DialogCancelState.Base(),
    ) : AbstractDialog<FragmentTwoFactorAuthBinding>(
        OpeningDialog(), ClosingDialog(), dialogIsCancel
    ) , TwoFactorUiState {
        override fun showDialog(view: FragmentTwoFactorAuthBinding) {
            TwoFactorFailureDialog(message) { dialogCanceled(view) }.show(view.root)
        }
    }


    @Parcelize
    private class ClosingDialog : Abstract(
        ProgressUiState.Hide, FabUiState.Show, ToolbarUiState.ShowNavIcon, ViewUiState.Enable
    )


    @Parcelize
    private class OpeningDialog : Abstract(
        ProgressUiState.Hide, FabUiState.Hide, ToolbarUiState.HideNavIcon, ViewUiState.Disable
    )

}