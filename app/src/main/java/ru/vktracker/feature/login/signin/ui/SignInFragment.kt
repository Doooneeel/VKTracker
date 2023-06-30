package ru.vktracker.feature.login.signin.ui

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.R
import ru.vktracker.core.ui.Throttle
import ru.vktracker.core.ui.dialog.AbstractDialog
import ru.vktracker.core.ui.fragment.BaseFragmentViewModel
import ru.vktracker.core.ui.fragment.OnThrottleClickListener
import ru.vktracker.core.ui.fragment.SingleTextWatcher
import ru.vktracker.core.ui.navigation.Navigation
import ru.vktracker.core.ui.view.common.SingleOnKeyListener
import ru.vktracker.core.ui.view.fab.FabViewState
import ru.vktracker.core.ui.view.progress.ProgressViewState
import ru.vktracker.core.ui.view.state.ViewState
import ru.vktracker.databinding.FragmentSignInBinding as Binding

/**
 * @author Danil Glazkov on 01.06.2023, 22:28
 */
@AndroidEntryPoint
class SignInFragment : BaseFragmentViewModel<Binding, SignInViewModel>(ID, Binding::inflate) {

    companion object {
        private const val ID = R.layout.fragment_sign_in
    }

    override val viewModel: SignInViewModel by viewModels<SignInViewModel.Base>()

    override fun firstRun() {
        binding.loginInputEditText.requestFocus()
        showKeyboard(binding.loginInputEditText)
    }

    override fun Binding.registerListeners(throttle: Throttle) {
        val inputViews = listOf(loginInputEditText, passwordInputEditText)

        toolBar.setNavigationOnClickListener(
            OnThrottleClickListener.SingleLonger {
                hideKeyboard(root)
                viewModel.navigateToWelcomeScreen()
            }
        )

        loginFloatingActionButton.setOnClickListener(
            OnThrottleClickListener.SingleLonger {
                viewModel.login(loginInputEditText.input(), passwordInputEditText.password())

                inputViews.forEach { inputEditText: TextInputEditText ->
                    inputEditText.clearFocus()
                }
            }
        )

        inputViews.forEach { inputEditText: TextInputEditText ->
            inputEditText.addTextChangedListener(
                SingleTextWatcher.OnTextChanged {
                    viewModel.changeInput(
                        loginInputEditText.input(),
                        passwordInputEditText.password()
                    )
                }
            )
        }

        passwordInputEditText.setOnKeyListener(
            SingleOnKeyListener.Enter { passwordEditText ->
                hideKeyboard(passwordEditText)
                passwordEditText.clearFocus()

                if (loginFloatingActionButton.isVisible) {
                    loginFloatingActionButton.performClick()
                }
            }
        )

    }

    override fun SignInViewModel.registerObservers() {
        observeDialog(viewLifecycleOwner) { alertDialog: AbstractDialog ->
            alertDialog.show(requireContext(), lifecycle)
        }

        observeFabViewState(viewLifecycleOwner) { viewState: FabViewState ->
            viewState.apply(binding.loginFloatingActionButton)
        }

        observeProgress(viewLifecycleOwner) { viewState: ProgressViewState ->
            viewState.apply(binding.progressIndicator)
        }

        observeViewState(viewLifecycleOwner) { viewState: ViewState ->
            viewState.apply(binding.passwordTextInputLayout, binding.loginTextInputLayout)
        }

        observeChildNavigation(viewLifecycleOwner) { navigation: Navigation ->
            navigation.navigate(navController)
        }

    }

}