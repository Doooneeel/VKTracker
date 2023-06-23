package ru.vktracker.feature.login.signin.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vktracker.core.ui.BaseViewModel
import ru.vktracker.core.ui.navigation.Screen
import ru.vktracker.core.ui.navigation.ScreenCommunication
import ru.vktracker.feature.login.signin.di.SingInModule.*
import javax.inject.Inject

/**
 * @author Danil Glazkov on 22.06.2023, 16:33
 */
interface SignInViewModel : BaseViewModel, SignInNavigation, ScreenCommunication.Observe {

    @HiltViewModel
    class Base @Inject constructor(
        @ModuleQualifier
        private val screenCommunication: ScreenCommunication,
        private val navigation: SignInNavigation,
    ) : ViewModel(), SignInViewModel, SignInNavigation by navigation {
        override fun observeScreen(owner: LifecycleOwner, observer: Observer<Screen>) =
            screenCommunication.observe(owner, observer)
    }
}