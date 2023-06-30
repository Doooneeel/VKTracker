package ru.vktracker.feature.login.signin.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.vktracker.core.ui.view.progress.ProgressCommunication
import ru.vktracker.core.ui.dialog.AbstractDialog
import ru.vktracker.core.ui.dialog.DialogCommunication
import ru.vktracker.core.ui.navigation.Navigation
import ru.vktracker.core.ui.navigation.NavigationCommunication
import ru.vktracker.core.ui.view.fab.FabViewState
import ru.vktracker.core.ui.view.fab.FabViewStateCommunication
import ru.vktracker.core.ui.view.progress.ProgressViewState
import ru.vktracker.core.ui.view.state.ViewState
import ru.vktracker.core.ui.view.state.ViewStateCommunication

/**
 * @author Danil Glazkov on 27.06.2023, 20:36
 */
interface SignInCommunications {

    interface Observe : NavigationCommunication.ObserveChild,
        FabViewStateCommunication.Observe,
        ProgressCommunication.Observe,
        DialogCommunication.Observe,
        ViewStateCommunication.Observe

    interface Update : ProgressCommunication.Update, FabViewStateCommunication.Update {
        fun putInterfaceViewState(state: ViewState)
    }

    interface Mutable : Observe, Update


    class Base(
        private val interfaceViewState: ViewStateCommunication,
        private val floatingButton: FabViewStateCommunication,
        private val dialog: DialogCommunication,
        private val progress: ProgressCommunication,
        private val childNavigation: NavigationCommunication,
    ) : Mutable {
        override fun putInterfaceViewState(state: ViewState) = interfaceViewState.put(state)

        override fun putProgressViewState(state: ProgressViewState) = progress.put(state)

        override fun putFabViewState(state: FabViewState) = floatingButton.put(state)

        override fun observeDialog(owner: LifecycleOwner, observer: Observer<AbstractDialog>) =
            dialog.observe(owner, observer)

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<ProgressViewState>) =
            progress.observe(owner, observer)

        override fun observeFabViewState(owner: LifecycleOwner, observer: Observer<FabViewState>) =
            floatingButton.observe(owner, observer)

        override fun observeChildNavigation(owner: LifecycleOwner, observer: Observer<Navigation>) =
            childNavigation.observe(owner, observer)

        override fun observeViewState(owner: LifecycleOwner, observer: Observer<ViewState>) =
            interfaceViewState.observe(owner, observer)
    }

}