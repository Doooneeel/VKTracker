package ru.vktracker.feature.profile.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.R
import ru.vktracker.core.ui.dialog.GenericDialog
import ru.vktracker.core.ui.fragment.BaseFragmentViewModel
import ru.vktracker.core.ui.navigation.Navigation
import ru.vktracker.core.ui.view.common.listeners.OnThrottleClickListener
import ru.vktracker.feature.profile.ui.state.ProfileUiState
import ru.vktracker.databinding.FragmentProfileBinding as Binding

/**
 * @author Danil Glazkov on 08.06.2023, 03:58
 */
@AndroidEntryPoint
class ProfileFragment : BaseFragmentViewModel<Binding, ProfileViewModel.Fragment>(ID) {

    companion object {
        private const val ID = R.layout.fragment_profile
    }

    override val viewModel: ProfileViewModel.Fragment by viewModels<ProfileViewModel.Base>()
    override val binding by viewBinding(Binding::bind)

    override val hasBottomBar: Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with (binding.settingsMenuItemLayout) {
            ProfileMenuItem.Settings().apply(this)
            root.setOnClickListener(
                OnThrottleClickListener.Medium(throttle) { viewModel.navigateToSettings() }
            )
        }

        with (binding.statisticsMenuItemLayout) {
            ProfileMenuItem.Statistics().apply(this)
            root.setOnClickListener(
                OnThrottleClickListener.Medium(throttle) { viewModel.navigateToStatistics() }
            )
        }

        with (binding.aboutMenuitemLayout) {
            ProfileMenuItem.About().apply(this)
            root.setOnClickListener(
                OnThrottleClickListener.Medium(throttle) { viewModel.navigateToAboutApp() }
            )
        }
    }

    override fun Binding.registerListeners() {
        logoutImageButton.setOnClickListener(
            OnThrottleClickListener.Medium(throttle) { viewModel.showLogoutDialog() }
        )
    }

    override fun ProfileViewModel.Fragment.registerObservers() {
        observe(viewLifecycleOwner) { uiState: ProfileUiState ->
            uiState.update(binding)
        }

        observeDialog(viewLifecycleOwner) { dialog: GenericDialog ->
            dialog.show(requireView(), childFragmentManager)
        }

        observeChildNavigation(viewLifecycleOwner) { navigation: Navigation ->
            navigation.navigate(navController)
        }

        observeMainNavigation(viewLifecycleOwner) { navigation: Navigation ->
            navigation.navigate(mainNavController)
        }
    }
}