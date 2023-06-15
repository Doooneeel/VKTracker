package ru.vktracker.main.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.ui.BaseViewModel
import ru.vktracker.core.ui.Init
import javax.inject.Inject

/**
 * @author Danil Glazkov on 01.06.2023, 22:29
 */
interface MainNavigationViewModel : BaseViewModel, LastSelectedMenuItemCommunication.Observe, Init {

    fun changeLastSelectedScreen(index: Int)


    @HiltViewModel
    class Base @Inject constructor(
        private val repository: MainNavigationRepository,
        private val communication: LastSelectedMenuItemCommunication,
        dispatchers: CoroutineDispatchers
    ) : BaseViewModel.Abstract(dispatchers),
        MainNavigationViewModel
    {
        override fun init(isFistRun: Boolean) {
            if (isFistRun) communication.put(
                LastSelectedMenuItem.Base(
                    repository.lastScreenIndex(DEFAULT_MENU_ITEM)
                )
            )
        }

        override fun changeLastSelectedScreen(index: Int) = repository.changeLastScreen(index)

        override fun observeLastSelectedItem(
            owner: LifecycleOwner,
            observer: Observer<LastSelectedMenuItem>
        ) = communication.observe(owner, observer)

        companion object {
            private const val DEFAULT_MENU_ITEM = 0
        }
    }
}