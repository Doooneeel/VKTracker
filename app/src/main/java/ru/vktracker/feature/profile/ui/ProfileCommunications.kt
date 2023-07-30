package ru.vktracker.feature.profile.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.vktracker.core.ui.dialog.DialogCommunication
import ru.vktracker.core.ui.dialog.GenericDialog
import ru.vktracker.core.ui.viewmodel.Communication
import ru.vktracker.core.ui.navigation.Navigation
import ru.vktracker.core.ui.navigation.NavigationCommunication
import ru.vktracker.feature.profile.ui.state.ProfileUiState
import ru.vktracker.feature.profile.ui.state.ProfileUiStateCommunication

/**
 * @author Danil Glazkov on 28.06.2023, 19:15
 */
interface ProfileCommunications {

    interface Update : Communication.Update<ProfileUiState>, DialogCommunication.Update

    interface Observe : Communication.Observe<ProfileUiState>, DialogCommunication.Observe,
        NavigationCommunication.CombinedObserve

    interface Combined : Update, Observe


    class Base(
        private val communication: ProfileUiStateCommunication,
        private val dialogCommunication: DialogCommunication,
        private val childNavigationCommunication: Communication.Observe<Navigation>,
        private val mainNavigationCommunication: Communication.Observe<Navigation>,
    ) : Combined {

        override fun put(value: ProfileUiState) = communication.put(value)

        override fun showDialog(dialog: GenericDialog) = dialogCommunication.put(dialog)

        override fun observe(owner: LifecycleOwner, observer: Observer<ProfileUiState>) =
            communication.observe(owner, observer)

        override fun observeDialog(owner: LifecycleOwner, observer: Observer<GenericDialog>) =
            dialogCommunication.observe(owner, observer)

        override fun observeChildNavigation(owner: LifecycleOwner, observer: Observer<Navigation>) =
            childNavigationCommunication.observe(owner, observer)

        override fun observeMainNavigation(owner: LifecycleOwner, observer: Observer<Navigation>) =
            mainNavigationCommunication.observe(owner, observer)
    }

}