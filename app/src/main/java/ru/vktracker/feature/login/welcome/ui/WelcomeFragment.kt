package ru.vktracker.feature.login.welcome.ui

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.R
import ru.vktracker.core.ui.BaseFragmentViewModel
import ru.vktracker.core.ui.OnThrottleClickListener
import ru.vktracker.core.ui.Throttle
import ru.vktracker.core.ui.navigation.Screen
import ru.vktracker.databinding.FragmentWelcomeBinding as Binding

/**
 * @author Danil Glazkov on 22.06.2023, 17:59
 */
@AndroidEntryPoint
class WelcomeFragment : BaseFragmentViewModel<Binding, WelcomeViewModel>(ID, Binding::inflate) {

    override val viewModel: WelcomeViewModel by viewModels<WelcomeViewModel.Base>()
    override val hasBottomBar = true


    override fun Binding.registerClickListeners(throttle: Throttle) {
        continueFloatingActionButton.setOnClickListener(
            OnThrottleClickListener.SingleLonger { viewModel.navigateToSingInFragment() }
        )
    }

    override fun WelcomeViewModel.registerObservers() {
        observeScreen(viewLifecycleOwner) { screen: Screen ->
            screen.navigate(navController)
        }
    }

    companion object {
        private val ID = R.layout.fragment_welcome
    }

}