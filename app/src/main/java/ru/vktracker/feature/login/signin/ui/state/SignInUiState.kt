package ru.vktracker.feature.login.signin.ui.state

import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.parcelize.Parcelize
import ru.vktracker.R
import ru.vktracker.core.ui.UiState
import ru.vktracker.databinding.FragmentSignInBinding

/**
 * @author Danil Glazkov on 09.07.2023, 17:28
 */
interface SignInUiState : UiState<FragmentSignInBinding> {

    @Parcelize
    object Nothing : UiState.AbstractNothing<FragmentSignInBinding>()


    @Parcelize
    object Login : SignInUiState {
        override fun update(binding: FragmentSignInBinding) = binding.run {
            toolBar.navigationIcon = null
            passwordTextInputLayout.isEnabled = false
            loginTextInputLayout.isEnabled = false
            loginFloatingActionButton.hide()
            progressIndicator.show()
        }
    }


    @Parcelize
    private object DialogCanceled : SignInUiState {
        override fun update(binding: FragmentSignInBinding) = binding.run {
            toolBar.setNavigationIcon(R.drawable.ic_arrow_back_tiny)
            passwordTextInputLayout.isEnabled = true
            loginTextInputLayout.isEnabled = true
            loginFloatingActionButton.show()
            loginFloatingActionButton.setIconResource(R.drawable.ic_refresh)
            loginFloatingActionButton.setText(R.string.to_retry)
        }
    }


    @Parcelize
    private object DialogNotCancel : SignInUiState {
        override fun update(binding: FragmentSignInBinding) = binding.run {
            toolBar.navigationIcon = null
            progressIndicator.hide()
            loginFloatingActionButton.hide()
            passwordTextInputLayout.isEnabled = false
            loginTextInputLayout.isEnabled = false
        }
    }


    @Parcelize
    private object DialogIsCancel : SignInUiState {
        override fun update(binding: FragmentSignInBinding) = binding.run {
            loginFloatingActionButton.setIconResource(R.drawable.ic_refresh)
            loginFloatingActionButton.setText(R.string.to_retry)
            toolBar.setNavigationIcon(R.drawable.ic_arrow_back_tiny)
            progressIndicator.hide()
            loginFloatingActionButton.show()
            passwordTextInputLayout.isEnabled = true
            loginTextInputLayout.isEnabled = true
        }
    }


    @Parcelize
    class ShowErrorDialog(
        private val message: String,
        private var dialogIsCancel: Boolean = false
    ) : SignInUiState {
        override fun update(binding: FragmentSignInBinding) {
            if (dialogIsCancel) {
                DialogIsCancel.update(binding)
            } else {
                DialogNotCancel.update(binding)

                MaterialAlertDialogBuilder(
                    binding.root.context,
                    R.style.ThemeOverlay_Material3_MaterialAlertDialog_IconPrimaryColor
                )
                    .setIcon(R.drawable.ic_error_outline)
                    .setTitle(R.string.auth_error)
                    .setMessage(message)
                    .setOnCancelListener {
                        DialogCanceled.update(binding)
                        dialogIsCancel = true
                    }
                    .setPositiveButton(R.string.close) { dialog, _ -> dialog.cancel() }
                    .show()
            }
        }
    }


    @Parcelize
    class UserInputChanged(private val isValid: Boolean) : SignInUiState {
        override fun update(binding: FragmentSignInBinding) =
            binding.loginFloatingActionButton.run { if (isValid) show() else hide() }
    }

}