package ru.vktracker.feature.login.twofactor.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.vktracker.R
import ru.vktracker.core.ui.fragment.BaseFragmentViewModel
import ru.vktracker.core.ui.navigation.Navigation
import ru.vktracker.core.ui.view.common.listeners.OnThrottleClickListener
import ru.vktracker.feature.login.twofactor.ui.state.TwoFactorUiState
import ru.vktracker.feature.login.twofactor.ui.view.code.listeners.CodeOnChangeListener
import ru.vktracker.databinding.FragmentTwoFactorAuthBinding as Binding

/**
 * @author Danil Glazkov on 22.06.2023, 18:10
 */
@AndroidEntryPoint
class TwoFactorAuthFragment : BaseFragmentViewModel<Binding, TwoFactorAuthViewModel>(ID) {

    companion object {
        private const val ID = R.layout.fragment_two_factor_auth
    }

    override val viewModel: TwoFactorAuthViewModel by viewModels<TwoFactorAuthViewModel.Base>()
    override val binding by viewBinding(Binding::bind)

    override fun handleFirstRun() {
        throttle.update()

        viewLifecycleOwner.lifecycleScope.launch {
            val animationDuration = resources.getInteger(R.integer.fragment_animation_duration)
            delay(animationDuration.toLong())

            binding.retryTimerChipButton.startTimer()
            binding.confirmCodeView.performClick()
        }
    }

    override fun Binding.registerListeners() {
        toolBar.setNavigationOnClickListener(
            OnThrottleClickListener.Medium(throttle) {
                viewModel.navigateToSignIn()
            }
        )

        confirmCodeView.setOnCodeChangeListener(
            CodeOnChangeListener.Focused(confirmCodeView) { _, isComplete: Boolean ->
                viewModel.changeConfirmationCodeState(isComplete)
            }
        )

        confirmFloatingActionButton.setOnClickListener(
            OnThrottleClickListener.Medium(throttle) {
                viewModel.confirmCode(confirmCodeView.code())
                confirmCodeView.clearFocus()
                hideKeyboard(it)
            }
        )

        retryTimerChipButton.setOnClickListener(
            OnThrottleClickListener.Medium(throttle) {
                retryTimerChipButton.startTimer()
                viewModel.resendCode()
            }
        )
    }

    override fun TwoFactorAuthViewModel.registerObservers() {
        observe(viewLifecycleOwner) { uiState: TwoFactorUiState ->
            uiState.update(binding)
        }
        observeChildNavigation(viewLifecycleOwner) { navigation: Navigation ->
            navigation.navigate(navController)
        }
    }

}