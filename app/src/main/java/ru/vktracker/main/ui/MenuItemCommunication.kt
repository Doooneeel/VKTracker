package ru.vktracker.main.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.vktracker.core.ui.Communication

/**
 * @author Danil Glazkov on 15.06.2023, 21:32
 */
interface MenuItemCommunication : Communication.Mutable<SelectedMenuItem> {

    interface Observe {
        fun observeMenuItem(owner: LifecycleOwner, observer: Observer<SelectedMenuItem>)
    }

    class Base : Communication.SingleUi<SelectedMenuItem>(), MenuItemCommunication
}