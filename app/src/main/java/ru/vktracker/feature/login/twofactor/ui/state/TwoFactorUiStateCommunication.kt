package ru.vktracker.feature.login.twofactor.ui.state

import androidx.lifecycle.SavedStateHandle
import ru.vktracker.core.ui.viewmodel.Communication

/**
 * @author Danil Glazkov on 10.07.2023, 15:11
 */
interface TwoFactorUiStateCommunication : Communication.Mutable<TwoFactorUiState> {

    class SavedState(
        handleUiState: HandleTwoFactorUiState,
        savedState: SavedStateHandle,
    ) : Communication.SavedStateUiWithHandler<TwoFactorUiState>(
        handleUiState,
        savedState,
        TwoFactorUiStateCommunication::class.java.simpleName
    ) , TwoFactorUiStateCommunication

}