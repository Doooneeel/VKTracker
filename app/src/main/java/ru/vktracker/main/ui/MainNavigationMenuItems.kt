package ru.vktracker.main.ui

import ru.vktracker.R
import ru.vktracker.core.ui.navigation.MenuItem
import ru.vktracker.core.ui.navigation.MenuItems

/**
 * @author Danil Glazkov on 15.06.2023, 22:47
 */
class MainNavigationMenuItems : MenuItems {

    private val items = listOf(
        MenuItem.Base(R.id.menu_item_account_users, R.id.navigation_account_users),
        MenuItem.Base(R.id.menu_item_tracking, R.id.navigation_tracking),
        MenuItem.Base(R.id.menu_item_settings, R.id.navigation_settings)
    )

    override fun items(): List<MenuItem> = items
}