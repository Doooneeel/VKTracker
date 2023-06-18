package ru.vktracker.core.ui

import android.view.MenuItem
import androidx.navigation.NavController
import com.google.android.material.navigation.NavigationBarView
import ru.vktracker.core.ui.navigation.GenericMenuItems

/**
 * @author Danil Glazkov on 18.06.2023, 22:50
 */
class GenericOnItemSelectedListener(
    private val genericMenuItems: GenericMenuItems,
    private val controller: NavController,
    private val onItemSelect: (Int) -> Unit,
) : NavigationBarView.OnItemSelectedListener {

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.isChecked) return false

        return genericMenuItems.apply(menuItem) { selectedItem, index ->
            selectedItem.navigate(controller)
            onItemSelect.invoke(index)
        }
    }

}