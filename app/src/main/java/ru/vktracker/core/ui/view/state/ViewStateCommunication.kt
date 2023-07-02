package ru.vktracker.core.ui.view.state

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import ru.vktracker.core.ui.viewmodel.Communication

/**
 * @author Danil Glazkov on 27.06.2023, 20:21
 */
interface ViewStateCommunication : Communication.Mutable<ViewState> {

    interface Observe {
        fun observeViewState(owner: LifecycleOwner, observer: Observer<ViewState>)
    }

    class Base : Communication.Ui<ViewState>(), ViewStateCommunication

    class SavedState(
        savedState: SavedStateHandle,
        key: String
    ) : Communication.SavedStateUi<ViewState>(savedState, key),
        ViewStateCommunication
}