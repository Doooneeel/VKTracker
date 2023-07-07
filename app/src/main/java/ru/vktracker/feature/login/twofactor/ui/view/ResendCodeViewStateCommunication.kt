package ru.vktracker.feature.login.twofactor.ui.view

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import ru.vktracker.core.ui.viewmodel.Communication

/**
 * @author Danil Glazkov on 07.07.2023, 19:01
 */
interface ResendCodeViewStateCommunication : Communication.Mutable<ResendCodeViewState> {

    interface Observe {
        fun observeResendCodeViewState(owner: LifecycleOwner, observer: Observer<ResendCodeViewState>)
    }

    interface Update {
        fun putResendCodeViewState(state: ResendCodeViewState)
    }

    class SavedState(
        savedState: SavedStateHandle
    ) : Communication.SavedStateUi<ResendCodeViewState>(savedState, "ResendCodeViewState"),
        ResendCodeViewStateCommunication
    {
        init { put(ResendCodeViewState.EmptyTimeEnable) }
    }

}