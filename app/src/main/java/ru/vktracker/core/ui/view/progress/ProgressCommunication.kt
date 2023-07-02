package ru.vktracker.core.ui.view.progress

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import ru.vktracker.core.ui.viewmodel.Communication

/**
 * @author Danil Glazkov on 27.06.2023, 23:49
 */
interface ProgressCommunication : Communication.Mutable<ProgressViewState> {

    interface Update {
        fun putProgressViewState(state: ProgressViewState)
    }

    interface Observe {
        fun observeProgress(owner: LifecycleOwner, observer: Observer<ProgressViewState>)
    }

    class Base : Communication.Ui<ProgressViewState>(), ProgressCommunication

    class SavedState(savedState: SavedStateHandle) : Communication.SavedStateUi<ProgressViewState>(
        savedState, "ProgressCommunicationViewState"
    )
}