package ru.vktracker.data.main

import ru.vktracker.data.core.cache.PreferencesDataStore
import ru.vktracker.main.ui.MainNavigationRepository

/**
 * @author Danil Glazkov on 15.06.2023, 21:24
 */
class BaseMainNavigationRepository(
    private val preferencesDataStore: PreferencesDataStore.Mutable<Int>
) : MainNavigationRepository {

    override fun lastSelectedPosition(default: Int): Int =
        preferencesDataStore.read(LAST_SELECTED_MENU_ITEM, default)

    override fun changeSelectedPosition(position: Int) =
        preferencesDataStore.save(LAST_SELECTED_MENU_ITEM, position)

    companion object {
        private const val LAST_SELECTED_MENU_ITEM = "lastSelectedMenuItemKey"
    }
}