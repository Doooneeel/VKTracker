package ru.vktracker.feature.login.signin.ui.state

import androidx.lifecycle.SavedStateHandle
import ru.vktracker.core.ui.viewmodel.Communication

/**
 * @author Danil Glazkov on 09.07.2023, 17:50
 */
interface SignInUiStateCommunication : Communication.Mutable<SignInUiState> {

    class SavedState(
        handleUiState: SignInHandleUiState,
        savedState: SavedStateHandle
    ) : Communication.SavedStateUiWithHandler<SignInUiState>(
        handleUiState,
        savedState,
        SignInUiStateCommunication::class.java.simpleName
    ) , SignInUiStateCommunication

}