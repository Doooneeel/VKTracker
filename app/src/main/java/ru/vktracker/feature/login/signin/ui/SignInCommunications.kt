package ru.vktracker.feature.login.signin.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.vktracker.core.ui.navigation.Navigation
import ru.vktracker.core.ui.navigation.NavigationCommunication
import ru.vktracker.core.ui.viewmodel.Communication
import ru.vktracker.feature.login.signin.ui.state.SignInUiState
import ru.vktracker.feature.login.signin.ui.state.SignInUiStateCommunication

/**
 * @author Danil Glazkov on 27.06.2023, 20:36
 */
interface SignInCommunications {

    interface Observe : NavigationCommunication.ObserveChild, Communication.Observe<SignInUiState>

    interface Update : Communication.Update<SignInUiState>

    interface Mutable : Observe, Update


    class Base(
        private val stateCommunication: SignInUiStateCommunication,
        private val childNavigationCommunication: NavigationCommunication,
    ) : Mutable {

        override fun put(value: SignInUiState) = stateCommunication.put(value)

        override fun observe(owner: LifecycleOwner, observer: Observer<SignInUiState>) =
            stateCommunication.observe(owner, observer)

        override fun observeChildNavigation(owner: LifecycleOwner, observer: Observer<Navigation>) =
            childNavigationCommunication.observe(owner, observer)
    }

}