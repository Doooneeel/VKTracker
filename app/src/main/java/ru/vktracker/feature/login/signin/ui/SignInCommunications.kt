package ru.vktracker.feature.login.signin.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.vktracker.core.ui.ProgressCommunication
import ru.vktracker.core.ui.dialog.AbstractDialog
import ru.vktracker.core.ui.dialog.DialogCommunication
import ru.vktracker.core.ui.navigation.Navigation
import ru.vktracker.core.ui.navigation.NavigationCommunication
import ru.vktracker.core.ui.view.fab.FabViewState
import ru.vktracker.core.ui.view.fab.FabViewStateCommunication
import ru.vktracker.core.ui.view.state.ViewState
import ru.vktracker.core.ui.view.state.ViewStateCommunication

/**
 * @author Danil Glazkov on 27.06.2023, 20:36
 */
interface SignInCommunications {

    interface Observe : NavigationCommunication.ObserveChild,
        FabViewStateCommunication.Observe,
        ProgressCommunication.Observe,
        DialogCommunication.Observe
    {
        fun observeInputViewState(owner: LifecycleOwner, observer: Observer<ViewState>)
    }

    interface Update : ProgressCommunication.Update, FabViewStateCommunication.Update {
        fun putInputViewState(state: ViewState)
    }

    interface Mutable : Observe, Update


    class Base(
        private val fabCommunication: FabViewStateCommunication,
        private val childNavigationCommunication: NavigationCommunication,
        private val dialogCommunication: DialogCommunication,
        private val inputViewStateCommunication: ViewStateCommunication,
        private val progressCommunication: ProgressCommunication
    ) : Mutable {

        override fun putInputViewState(state: ViewState) = inputViewStateCommunication.put(state)

        override fun putProgressViewState(state: ViewState) = progressCommunication.put(state)

        override fun putFabViewState(state: FabViewState) = fabCommunication.put(state)

        override fun observeDialog(owner: LifecycleOwner, observer: Observer<AbstractDialog>) =
            dialogCommunication.observe(owner, observer)

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<ViewState>) =
            progressCommunication.observe(owner, observer)

        override fun observeFabViewState(owner: LifecycleOwner, observer: Observer<FabViewState>) =
            fabCommunication.observe(owner, observer)

        override fun observeChildNavigation(owner: LifecycleOwner, observer: Observer<Navigation>) =
            childNavigationCommunication.observe(owner, observer)

        override fun observeInputViewState(owner: LifecycleOwner, observer: Observer<ViewState>) =
            inputViewStateCommunication.observe(owner, observer)
    }

}