package ru.vktracker.main.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vktracker.core.ui.BaseViewModel
import ru.vktracker.core.ui.Init
import ru.vktracker.core.ui.navigation.Screen
import ru.vktracker.core.ui.navigation.ScreenCommunication
import ru.vktracker.main.di.MainModule.*
import ru.vktracker.main.domain.MainInteractor
import javax.inject.Inject

/**
 * @author Danil Glazkov on 21.06.2023, 14:34
 */
interface MainViewModel : BaseViewModel, ScreenCommunication.Observe, Init {

    @HiltViewModel
    class Base @Inject constructor(
        private val mainInteractor: MainInteractor,
        @ModuleQualifier
        private val screenCommunication: ScreenCommunication,
    ) : ViewModel(), MainViewModel {

        override fun init(isFistRun: Boolean) { }

        override fun observeScreen(owner: LifecycleOwner, observer: Observer<Screen>) =
            screenCommunication.observe(owner, observer)
    }

}