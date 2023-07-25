package ru.vktracker.feature.login.signin.ui

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.vktracker.R
import ru.vktracker.core.ui.fragment.BaseFragmentViewModel
import ru.vktracker.core.ui.navigation.Navigation
import ru.vktracker.core.ui.view.common.listeners.OnThrottleClickListener
import ru.vktracker.core.ui.view.common.listeners.SingleEditorActionListener
import ru.vktracker.core.ui.view.text.SingleTextWatcher
import ru.vktracker.feature.login.signin.ui.state.SignInUiState
import ru.vktracker.databinding.FragmentSignInBinding as Binding

/**
 * @author Danil Glazkov on 01.06.2023, 22:28
 */
@AndroidEntryPoint
class SignInFragment : BaseFragmentViewModel<Binding, SignInViewModel>(ID) {

    companion object {
        private const val ID = R.layout.fragment_sign_in
    }

    override val viewModel: SignInViewModel by viewModels<SignInViewModel.Base>()
    override val binding: Binding by viewBinding(Binding::bind)

    override fun handleFirstRun() {
        throttle.update()

        viewLifecycleOwner.lifecycleScope.launch {
            delay(resources.getInteger(R.integer.fragment_animation_duration).toLong())

            binding.loginInputEditText.requestFocus()
            showKeyboard(binding.loginInputEditText)
        }
    }

    override fun Binding.registerListeners() {
        val inputViews = listOf(loginInputEditText, passwordInputEditText)

        toolBar.setNavigationOnClickListener(OnThrottleClickListener.Medium(throttle) {
            viewModel.navigateToWelcomeScreen()
            hideKeyboard(root)
        })

        loginFloatingActionButton.setOnClickListener(OnThrottleClickListener.Medium(throttle) {
            viewModel.login(loginInputEditText.input(), passwordInputEditText.password())
            requireActivity().currentFocus?.clearFocus()
        })

        inputViews.forEach { inputEditText: TextInputEditText ->
            inputEditText.addTextChangedListener(
                SingleTextWatcher.FocusedOnTextChanged(inputEditText) {
                    viewModel.changeInput(
                        loginInputEditText.input(),
                        passwordInputEditText.password()
                    )
                }
            )
        }

        passwordInputEditText.setOnEditorActionListener(SingleEditorActionListener.Done {
            passwordInputEditText.clearFocus()
            hideKeyboard(root)

            if (loginFloatingActionButton.isVisible) {
                loginFloatingActionButton.performClick()
            }
        })
    }

    override fun SignInViewModel.registerObservers() {
        observe(viewLifecycleOwner) { uiState: SignInUiState ->
            uiState.update(binding)
        }
        observeChildNavigation(viewLifecycleOwner) { navigation: Navigation ->
            navigation.navigate(navController)
        }
    }

}