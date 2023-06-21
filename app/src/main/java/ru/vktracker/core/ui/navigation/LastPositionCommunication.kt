package ru.vktracker.core.ui.navigation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.vktracker.core.ui.Communication

/**
 * @author Danil Glazkov on 21.06.2023, 15:52
 */
interface LastPositionCommunication : Communication.Mutable<LastPosition> {

    interface Observe {
        fun observeLastPosition(owner: LifecycleOwner, observer: Observer<LastPosition>)
    }

    class Base : Communication.SingleUi<LastPosition>(), LastPositionCommunication
}