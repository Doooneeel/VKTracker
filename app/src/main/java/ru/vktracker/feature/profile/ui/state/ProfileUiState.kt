package ru.vktracker.feature.profile.ui.state

import kotlinx.parcelize.Parcelize
import ru.vktracker.core.ui.state.UiState
import ru.vktracker.databinding.FragmentProfileBinding
import ru.vktracker.feature.profile.ui.ProfileUi

/**
 * @author Danil Glazkov on 26.07.2023, 0:33
 */
interface ProfileUiState : UiState<FragmentProfileBinding> {

    @Parcelize
    class Initial(private val profileUi: ProfileUi) : ProfileUiState {
        override fun update(view: FragmentProfileBinding) {
            profileUi.apply(view.userIdTextView, view.usernameTextView, view.avatarImageView)
        }
    }

}