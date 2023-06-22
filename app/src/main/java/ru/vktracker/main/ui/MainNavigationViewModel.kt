package ru.vktracker.main.ui

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.ui.BaseViewModel
import ru.vktracker.core.ui.Init
import ru.vktracker.core.ui.navigation.LastPosition
import ru.vktracker.core.ui.navigation.LastPositionCommunication
import ru.vktracker.main.di.MainModule
import ru.vktracker.main.di.MainModule.*
import javax.inject.Inject

/**
 * @author Danil Glazkov on 01.06.2023, 22:29
 */
interface MainNavigationViewModel : BaseViewModel, LastPositionCommunication.Observe, Init {

    fun changeLastSelectedTab(position: Int)


    @HiltViewModel
    class Base @Inject constructor(
        private val repository: MainNavigationRepository,
        @ModuleQualifier
        private val communication: LastPositionCommunication,
        dispatchers: CoroutineDispatchers
    ) : BaseViewModel.Abstract(dispatchers),
        MainNavigationViewModel
    {
        override fun init(isFistRun: Boolean) {
            if (isFistRun) {
                val lastPosition = LastPosition.Base(repository.lastSelectedPosition(default = 0))
                communication.put(lastPosition)
            }
        }

        override fun changeLastSelectedTab(position: Int) =
            repository.changeSelectedPosition(position)

        override fun observeLastPosition(owner: LifecycleOwner, observer: Observer<LastPosition>) =
            communication.observe(owner, observer)
    }
}