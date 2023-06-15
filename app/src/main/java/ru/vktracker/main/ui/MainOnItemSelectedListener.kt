package ru.vktracker.main.ui

import android.view.MenuItem
import androidx.navigation.NavController
import com.google.android.material.navigation.NavigationBarView
import ru.vktracker.core.ui.navigation.MenuItems

/**
 * @author Danil Glazkov on 02.06.2023, 17:09
 */
class MainOnItemSelectedListener(
    private val menuItems: MenuItems,
    private val controller: NavController,
    private val onItemSelect: (Int) -> Unit
) : NavigationBarView.OnItemSelectedListener {

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.isChecked) return false

        menuItems.items().forEachIndexed { index: Int, item ->
            if (item.matches(menuItem.itemId)) {
                item.navigate(controller)
                onItemSelect.invoke(index)
                return true
            }
        }
        return false
    }

}