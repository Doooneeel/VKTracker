package ru.vktracker.feature.login.twofactor.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.vktracker.core.ui.navigation.Navigation
import ru.vktracker.core.ui.navigation.NavigationCommunication
import ru.vktracker.core.ui.viewmodel.Communication
import ru.vktracker.feature.login.twofactor.ui.state.TwoFactorUiState
import ru.vktracker.feature.login.twofactor.ui.state.TwoFactorUiStateCommunication

/**
 * @author Danil Glazkov on 26.06.2023, 19:50
 */
interface TwoFactorCommunications {

    interface Update : Communication.Update<TwoFactorUiState>

    interface Observe : NavigationCommunication.ObserveChild,
        Communication.Observe<TwoFactorUiState>

    interface Mutable : Update, Observe


    class Base(
        private val communication: TwoFactorUiStateCommunication,
        private val navigationCommunication: NavigationCommunication,
    ) : Mutable {

        override fun put(value: TwoFactorUiState) = communication.put(value)

        override fun observe(owner: LifecycleOwner, observer: Observer<TwoFactorUiState>) =
            communication.observe(owner, observer)

        override fun observeChildNavigation(owner: LifecycleOwner, observer: Observer<Navigation>) =
            navigationCommunication.observe(owner, observer)
    }

}