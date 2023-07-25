package ru.vktracker.feature.login.signin.ui.state

import ru.vktracker.R
import ru.vktracker.core.ui.HandleUiState
import ru.vktracker.core.ui.resources.ProvideString

/**
 * @author Danil Glazkov on 10.07.2023, 13:36
 */
interface SignInHandleUiState : HandleUiState<SignInUiState> {

    class Base(private val resources: ProvideString.Single) : SignInHandleUiState {
        override fun handle(savedState: SignInUiState?): SignInUiState? {
            return if (savedState is SignInUiState.Login) {
                SignInUiState.FailDialog(resources.string(R.string.timed_out_error))
            } else {
                savedState
            }
        }
    }
}