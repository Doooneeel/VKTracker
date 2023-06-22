package ru.vktracker.core.ui.navigation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.vktracker.core.ui.Communication

/**
 * @author Danil Glazkov on 21.06.2023, 14:34
 */
interface ScreenCommunication : Communication.Mutable<Screen> {

    interface Observe {
        fun observeScreen(owner: LifecycleOwner, observer: Observer<Screen>)
    }

    class Base : Communication.SingleUi<Screen>(), ScreenCommunication
}