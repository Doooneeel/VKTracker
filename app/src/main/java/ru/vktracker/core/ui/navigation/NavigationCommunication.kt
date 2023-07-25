package ru.vktracker.core.ui.navigation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.vktracker.core.ui.viewmodel.Communication

/**
 * @author Danil Glazkov on 21.06.2023, 14:34
 */
interface NavigationCommunication : Communication.Mutable<Navigation> {

    interface ObserveChild {
        fun observeChildNavigation(owner: LifecycleOwner, observer: Observer<Navigation>)
    }

    interface ObserveMain {
        fun observeMainNavigation(owner: LifecycleOwner, observer: Observer<Navigation>)
    }

    interface CombineObserve : ObserveChild, ObserveMain

    class Ui : Communication.SingleUi<Navigation>(), NavigationCommunication

}