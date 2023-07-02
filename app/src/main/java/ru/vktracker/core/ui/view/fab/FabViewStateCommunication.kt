package ru.vktracker.core.ui.view.fab

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import ru.vktracker.core.ui.viewmodel.Communication

/**
 * @author Danil Glazkov on 25.06.2023, 16:40
 */
interface FabViewStateCommunication : Communication.Mutable<FabViewState> {

    interface Update {
        fun putFabViewState(state: FabViewState)
    }

    interface Observe {
        fun observeFabViewState(owner: LifecycleOwner, observer: Observer<FabViewState>)
    }

    class Base : Communication.Ui<FabViewState>(), FabViewStateCommunication

    class SavedState(savedState: SavedStateHandle) : Communication.SavedStateUi<FabViewState>(
        savedState, "FabViewStateCommunication"
    ), FabViewStateCommunication
}