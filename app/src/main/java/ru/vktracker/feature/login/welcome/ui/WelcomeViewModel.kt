package ru.vktracker.feature.login.welcome.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vktracker.R
import ru.vktracker.core.ui.BaseViewModel
import ru.vktracker.core.ui.navigation.Screen
import ru.vktracker.core.ui.navigation.ScreenCommunication
import ru.vktracker.feature.login.welcome.di.WelcomeModule.*
import javax.inject.Inject

/**
 * @author Danil Glazkov on 22.06.2023, 18:18
 */
interface WelcomeViewModel : BaseViewModel, ScreenCommunication.Observe {

    fun navigateToSingInFragment()


    @HiltViewModel
    class Base @Inject constructor(
        @ModuleQualifier
        private val communication: ScreenCommunication
    ) : ViewModel(), WelcomeViewModel {

        override fun navigateToSingInFragment() = communication.put(
            Screen.Base(R.id.action_welcome_to_signIn)
        )

        override fun observeScreen(owner: LifecycleOwner, observer: Observer<Screen>) =
            communication.observe(owner, observer)
    }
}