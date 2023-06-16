package ru.vktracker.main.data

import ru.vktracker.data.core.cache.PreferencesDataStore
import ru.vktracker.main.ui.MainNavigationRepository

/**
 * @author Danil Glazkov on 15.06.2023, 21:24
 */
class BaseMainNavigationRepository(
    private val preferencesDataStore: PreferencesDataStore.Mutable<Int>
) : MainNavigationRepository {

    override fun lastScreenIndex(default: Int): Int =
        preferencesDataStore.read(LAST_SELECTED_MENU_ITEM, default)

    override fun changeLastScreen(index: Int) =
        preferencesDataStore.save(LAST_SELECTED_MENU_ITEM, index)

    companion object {
        private const val LAST_SELECTED_MENU_ITEM = "lastSelectedMenuItemKey"
    }
}