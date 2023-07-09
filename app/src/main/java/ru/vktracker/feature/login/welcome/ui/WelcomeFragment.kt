package ru.vktracker.feature.login.welcome.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.R
import ru.vktracker.core.ui.fragment.BaseFragmentViewModel
import ru.vktracker.core.ui.view.common.listeners.OnThrottleClickListener
import ru.vktracker.core.ui.navigation.Navigation
import ru.vktracker.databinding.FragmentWelcomeBinding as Binding

/**
 * @author Danil Glazkov on 22.06.2023, 17:59
 */
@AndroidEntryPoint
class WelcomeFragment : BaseFragmentViewModel<Binding, WelcomeViewModel>(ID) {

    companion object {
        private const val ID = R.layout.fragment_welcome
    }

    override val viewModel: WelcomeViewModel by viewModels<WelcomeViewModel.Base>()
    override val binding: Binding by viewBinding(Binding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        WelcomeUiState.Tracking.update(binding.welcomeItemTracking)
        WelcomeUiState.Design.update(binding.welcomeItemDesign)

    }

    override fun Binding.registerListeners() {
        continueFloatingActionButton.setOnClickListener(
            OnThrottleClickListener.SingleLonger { viewModel.navigateToSingInFragment() }
        )
    }

    override fun WelcomeViewModel.registerObservers() {
        observeChildNavigation(viewLifecycleOwner) { navigation: Navigation ->
            navigation.navigate(navController)
        }
    }

}