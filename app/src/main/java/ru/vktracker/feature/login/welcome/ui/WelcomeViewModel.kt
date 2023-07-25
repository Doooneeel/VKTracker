package ru.vktracker.feature.login.welcome.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vktracker.R
import ru.vktracker.core.ui.viewmodel.BaseViewModel
import ru.vktracker.core.ui.navigation.Navigation
import ru.vktracker.core.ui.navigation.NavigationCommunication
import javax.inject.Inject

/**
 * @author Danil Glazkov on 22.06.2023, 18:18
 */
interface WelcomeViewModel : BaseViewModel, NavigationCommunication.ObserveChild {

    fun navigateToSingInFragment()


    @HiltViewModel
    class Base @Inject constructor(
        private val communication: NavigationCommunication,
    ) : ViewModel(), WelcomeViewModel {

        override fun navigateToSingInFragment() = communication.put(
            Navigation.ID(R.id.action_welcome_to_signIn)
        )

        override fun observeChildNavigation(owner: LifecycleOwner, observer: Observer<Navigation>) =
            communication.observe(owner, observer)
    }
}