package ru.vktracker.feature.profile.ui

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.R
import ru.vktracker.core.ui.BaseFragmentViewModel
import ru.vktracker.core.ui.Throttle
import ru.vktracker.core.ui.OnThrottleClickListener
import ru.vktracker.core.ui.dialog.AbstractAlertDialog
import ru.vktracker.core.ui.navigation.Screen
import ru.vktracker.databinding.FragmentProfileBinding as Binding

/**
 * @author Danil Glazkov on 08.06.2023, 03:58
 */
@AndroidEntryPoint
class ProfileFragment : BaseFragmentViewModel<Binding, ProfileViewModel>(ID, Binding::inflate) {

    override val viewModel by viewModels<ProfileViewModel.Base>()
    override val hasBottomBar: Boolean = true

    override fun Binding.registerClickListeners(throttle: Throttle) {

        logoutImageButton.setOnClickListener(OnThrottleClickListener.Medium(throttle) {
            viewModel.showLogoutDialog()
        })

        settingsMenuItemLayout.setOnClickListener(
            OnThrottleClickListener.Medium(throttle) { viewModel.navigateToSettings() }
        )

        statisticsMenuItemLayout.setOnClickListener(
            OnThrottleClickListener.Medium(throttle) { viewModel.navigateToStatistics() }
        )

        aboutAppMenuItemLayout.setOnClickListener(
            OnThrottleClickListener.Medium(throttle) { viewModel.navigateToAboutApp() }
        )
    }

    override fun ProfileViewModel.registerObservers() {
        observeProfileUi(viewLifecycleOwner) { profile: ProfileUi ->
            profile.apply(binding.userIdTextView, binding.usernameTextView, binding.avatarImageView)
            binding.usernameTextView.isSelected = true
        }

        observeLogoutDialog(viewLifecycleOwner) { logoutDialog: AbstractAlertDialog ->
            logoutDialog.show(requireContext(), lifecycle)
        }

        observeScreen(viewLifecycleOwner) { screen: Screen ->
            screen.navigate(navController)
        }
    }

    companion object {
        private val ID = R.layout.fragment_profile
    }
}