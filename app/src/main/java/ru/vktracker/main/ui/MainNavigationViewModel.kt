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
interface MainNavigationViewModel : BaseViewModel, MenuItemCommunication.Observe, Init {

    fun changeLastSelectedTab(position: Int)


    @HiltViewModel
    class Base @Inject constructor(
        private val repository: MainNavigationRepository,
        private val communication: MenuItemCommunication,
        dispatchers: CoroutineDispatchers
    ) : BaseViewModel.Abstract(dispatchers),
        MainNavigationViewModel
    {
        override fun init(isFistRun: Boolean) {
            if (isFistRun) communication.put(
                SelectedMenuItem.Base(
                    repository.lastSelectedPosition(DEFAULT_MENU_ITEM)
                )
            )
        }

        override fun changeLastSelectedTab(position: Int) =
            repository.changeSelectedPosition(position)

        override fun observeMenuItem(
            owner: LifecycleOwner,
            observer: Observer<SelectedMenuItem>
        ) = communication.observe(owner, observer)

        companion object {
            private const val DEFAULT_MENU_ITEM = 0
        }
    }
}