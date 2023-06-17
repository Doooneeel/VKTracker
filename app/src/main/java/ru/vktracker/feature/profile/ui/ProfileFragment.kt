package ru.vktracker.feature.profile.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.R
import ru.vktracker.core.ui.BaseFragment
import ru.vktracker.core.ui.OnThrottleClickListener
import ru.vktracker.databinding.FragmentProfileBinding as Binding

/**
 * @author Danil Glazkov on 08.06.2023, 03:58
 */
@AndroidEntryPoint
class ProfileFragment : BaseFragment<Binding, ProfileViewModel>(ID, Binding::inflate) {

    override val viewModel by viewModels<ProfileViewModel.Base>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observeProfileUi(viewLifecycleOwner) { profile: ProfileUi ->
            profile.apply(binding.userIdTextView, binding.usernameTextView, binding.avatarImageView)
        }

        binding.logoutImageButton.setOnClickListener(OnThrottleClickListener.SingleMedium {
            //todo handle click
        })

        viewModel.init(savedInstanceState == null)
    }

    companion object {
        private val ID = R.layout.fragment_profile
    }
}