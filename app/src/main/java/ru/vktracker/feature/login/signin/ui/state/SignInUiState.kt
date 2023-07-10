package ru.vktracker.feature.login.signin.ui.state

import kotlinx.parcelize.Parcelize
import ru.vktracker.R
import ru.vktracker.core.ui.UiState
import ru.vktracker.databinding.FragmentSignInBinding
import ru.vktracker.feature.login.signin.ui.dialogs.SignInFailDialog

/**
 * @author Danil Glazkov on 09.07.2023, 17:28
 */
interface SignInUiState : UiState<FragmentSignInBinding> {

    @Parcelize
    object Nothing : UiState.AbstractNothing<FragmentSignInBinding>(), SignInUiState


    @Parcelize
    class Enter : SignInUiState {
        override fun update(binding: FragmentSignInBinding) {
            binding.progressIndicator.show()
            Inactive.update(binding)
        }
    }


    @Parcelize
    object ValidInput : SignInUiState {
        override fun update(binding: FragmentSignInBinding) =
            binding.loginFloatingActionButton.run {
                isClickable = true
                show()
            }
    }


    @Parcelize
    object InvalidInput : SignInUiState {
        override fun update(binding: FragmentSignInBinding) =
            binding.loginFloatingActionButton.run {
                isClickable = false
                hide()
            }
    }


    @Parcelize
    object Inactive : SignInUiState {
        override fun update(binding: FragmentSignInBinding) = binding.run {
            loginFloatingActionButton.hide()

            toolBar.navigationIcon = null

            passwordTextInputLayout.isEnabled = false
            loginTextInputLayout.isEnabled = false
        }
    }

    @Parcelize
    object Active : SignInUiState {
        override fun update(binding: FragmentSignInBinding) = binding.run {
            progressIndicator.hide()

            toolBar.setNavigationIcon(R.drawable.ic_arrow_back_tiny)

            passwordTextInputLayout.isEnabled = true
            loginTextInputLayout.isEnabled = true
        }
    }


    @Parcelize
    class ErrorDialog(
        private val message: String,
        private var dialogIsCancel: Boolean = false
    ) : SignInUiState {

        private fun active(binding: FragmentSignInBinding) = binding.run {
            loginFloatingActionButton.setText(R.string.to_retry)
            loginFloatingActionButton.show()

            Active.update(binding)
        }

        override fun update(binding: FragmentSignInBinding) {
            if (dialogIsCancel) {
                active(binding)
            } else {
                binding.progressIndicator.hide()
                Inactive.update(binding)

                val dialog = SignInFailDialog(message) {
                    dialogIsCancel = true
                    active(binding)
                }
                dialog.show(binding.root)
            }
        }
    }




}