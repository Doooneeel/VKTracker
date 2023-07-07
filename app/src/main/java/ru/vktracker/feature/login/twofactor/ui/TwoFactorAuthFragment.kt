package ru.vktracker.feature.login.twofactor.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.vktracker.R
import ru.vktracker.core.ui.dialog.AbstractDialog
import ru.vktracker.core.ui.fragment.BaseFragmentViewModel
import ru.vktracker.core.ui.navigation.Navigation
import ru.vktracker.core.ui.text.Message
import ru.vktracker.core.ui.view.common.listeners.OnThrottleClickListener
import ru.vktracker.core.ui.view.fab.FabViewState
import ru.vktracker.core.ui.view.progress.ProgressViewState
import ru.vktracker.feature.login.twofactor.ui.view.ResendCodeViewState
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
            binding.confirmCodeView.requestFocus()
        }
    }

    override fun Binding.registerListeners() {
        toolBar.setNavigationOnClickListener(
            OnThrottleClickListener.Medium(throttle) {
                viewModel.navigateToSignIn()
            }
        )

        confirmCodeView.setOnCodeChangeListener { code: String, isComplete: Boolean ->
            viewModel.changeInputCode(code, isComplete)
        }

        confirmFloatingActionButton.setOnClickListener(
            OnThrottleClickListener.Medium(throttle) {
                viewModel.confirmCode(confirmCodeView.code())
                hideKeyboard(it)
            }
        )

        retryChipButton.setOnClickListener(
            OnThrottleClickListener.Medium(throttle) {
                viewModel.resendCode()
            }
        )
    }

    override fun TwoFactorAuthViewModel.registerObservers() {
        observeChildNavigation(viewLifecycleOwner) { navigation: Navigation ->
            navigation.navigate(navController)
        }

        observeAboutSendingMessage(viewLifecycleOwner) { message: Message ->
            message.apply(binding.descriptionTextView)
        }

        observeFabViewState(viewLifecycleOwner) { viewState: FabViewState ->
            viewState.apply(binding.confirmFloatingActionButton)
        }

        observeResendCodeViewState(viewLifecycleOwner) { viewState: ResendCodeViewState ->
            viewState.apply(binding.retryChipButton)
        }

        observeDialog(viewLifecycleOwner) { dialog: AbstractDialog ->
            dialog.show(requireContext(), lifecycle)
        }

        observeProgress(viewLifecycleOwner) { progressViewState: ProgressViewState ->
            progressViewState.apply(binding.progressIndicator)
        }
    }

}