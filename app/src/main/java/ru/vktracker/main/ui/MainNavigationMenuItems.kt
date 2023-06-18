package ru.vktracker.main.ui

import ru.vktracker.R
import ru.vktracker.core.ui.navigation.GenericMenuItem
import ru.vktracker.core.ui.navigation.GenericMenuItems

/**
 * @author Danil Glazkov on 15.06.2023, 22:47
 */
class MainNavigationMenuItems : GenericMenuItems.Abstract() {
    override val genericItems = listOf(
        GenericMenuItem.Base(R.id.menu_item_account_users, R.id.navigation_account_users),
        GenericMenuItem.Base(R.id.menu_item_tracking, R.id.navigation_tracking),
        GenericMenuItem.Base(R.id.menu_item_settings, R.id.navigation_settings)
    )
}