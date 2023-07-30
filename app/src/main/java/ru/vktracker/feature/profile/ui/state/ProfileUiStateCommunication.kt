package ru.vktracker.feature.profile.ui.state

import androidx.lifecycle.SavedStateHandle
import ru.vktracker.core.ui.viewmodel.Communication

/**
 * @author Danil Glazkov on 17.06.2023, 07:18
 */
interface ProfileUiStateCommunication : Communication.Mutable<ProfileUiState> {

    class SavedState(
        savedStateHandle: SavedStateHandle
    ) : Communication.SavedStateUi<ProfileUiState>(
        savedStateHandle,
        "ProfileUiStateCommunication"
    ) , ProfileUiStateCommunication

}