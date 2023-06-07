package ru.vktracker.main.ui

import android.view.MenuItem
import androidx.navigation.NavController
import com.google.android.material.navigation.NavigationBarView
import ru.vktracker.R

/**
 * @author Danil Glazkov on 02.06.2023, 17:09
 */
class MainOnItemSelectedListener(
    private val controller: NavController,
) : NavigationBarView.OnItemSelectedListener {

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val newSelectedItemId = item.itemId

        controller.navigate(
            when (newSelectedItemId) {
                R.id.menu_item_friends -> R.id.navigation_account_users
                R.id.menu_item_tracking -> R.id.navigation_tracking
                R.id.menu_item_settings -> R.id.navigation_settings
                else -> throw throw IllegalStateException("Unknown fragment: $item")
            }
        )

        return true
    }

}