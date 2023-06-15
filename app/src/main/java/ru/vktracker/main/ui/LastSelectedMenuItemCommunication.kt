package ru.vktracker.main.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.vktracker.core.ui.Communication

/**
 * @author Danil Glazkov on 15.06.2023, 21:32
 */
interface LastSelectedMenuItemCommunication : Communication.Mutable<LastSelectedMenuItem> {

    interface Observe {
        fun observeLastSelectedItem(owner: LifecycleOwner, observer: Observer<LastSelectedMenuItem>)
    }

    class Base : Communication.SingleUi<LastSelectedMenuItem>(), LastSelectedMenuItemCommunication
}