package ru.vktracker.feature.login.signin.ui

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.R
import ru.vktracker.core.ui.BaseFragmentViewModel
import ru.vktracker.core.ui.Throttle
import ru.vktracker.core.ui.OnThrottleClickListener
import ru.vktracker.core.ui.SingleTextWatcher
import ru.vktracker.core.ui.dialog.AbstractDialog
import ru.vktracker.core.ui.navigation.Navigation
import ru.vktracker.core.ui.view.fab.FabViewState
import ru.vktracker.core.ui.view.state.ViewState
import ru.vktracker.databinding.FragmentSignInBinding as Binding

/**
 * @author Danil Glazkov on 01.06.2023, 22:28
 */
@AndroidEntryPoint
class SignInFragment : BaseFragmentViewModel<Binding, SignInViewModel>(ID, Binding::inflate) {

    override val viewModel: SignInViewModel by viewModels<SignInViewModel.Base>()

    override fun firstRun() {
        binding.loginInputEditText.requestFocus()
        showKeyboard(binding.loginInputEditText)
    }

    override fun Binding.registerListeners(throttle: Throttle) {
        toolBar.setNavigationOnClickListener(
            OnThrottleClickListener.Longer(throttle) {
                hideKeyboard(root)
                viewModel.navigateToWelcomeScreen()
            }
        )

        loginFloatingActionButton.setOnClickListener(
            OnThrottleClickListener.Longer(throttle) {
                viewModel.login(loginInputEditText.input(), passwordInputEditText.password())

                hideKeyboard(root)
                loginInputEditText.clearFocus()
                passwordInputEditText.clearFocus()
            }
        )

        val inputTextWatcher = SingleTextWatcher.OnTextChanged {
            viewModel.changeInput(loginInputEditText.input(), passwordInputEditText.password())
        }

        loginInputEditText.addTextChangedListener(inputTextWatcher)
        passwordInputEditText.addTextChangedListener(inputTextWatcher)
    }

    override fun SignInViewModel.registerObservers() {

        observeDialog(viewLifecycleOwner) { alertDialog: AbstractDialog ->
            alertDialog.show(requireContext(), lifecycle)
        }

        observeFabViewState(viewLifecycleOwner) { viewState: FabViewState ->
            viewState.apply(binding.loginFloatingActionButton)
        }

        observeProgress(viewLifecycleOwner) { viewState: ViewState ->
            viewState.apply(binding.progressIndicator)
        }

        observeInputViewState(viewLifecycleOwner) { viewState: ViewState ->
            viewState.apply(binding.passwordTextInputLayout, binding.loginTextInputLayout)
        }

        observeChildNavigation(viewLifecycleOwner) { navigation: Navigation ->
            navigation.navigate(navController)
        }

    }

    companion object {
        private val ID = R.layout.fragment_sign_in
    }

}