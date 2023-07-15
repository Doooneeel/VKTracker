package ru.vktracker.feature.login.twofactor.ui.state

import ru.vktracker.R
import ru.vktracker.core.ui.HandleUiState
import ru.vktracker.core.ui.resources.ProvideString

/**
 * @author Danil Glazkov on 10.07.2023, 15:12
 */
interface HandleTwoFactorUiState : HandleUiState<TwoFactorUiState> {

    class Base(
        private val resources: ProvideString.Single,
    ) : HandleTwoFactorUiState {
        override fun handle(savedState: TwoFactorUiState?): TwoFactorUiState? {
            return if (savedState is TwoFactorUiState.ConfirmCode) {
                TwoFactorUiState.FailDialog(
                    message = resources.string(R.string.timed_out_error)
                )
            } else {
                savedState
            }
        }
    }
}