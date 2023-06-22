package ru.vktracker.core.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.MenuItem
import androidx.core.view.forEachIndexed
import androidx.core.view.get
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * @author Danil Glazkov on 21.06.2023, 15:46
 */
class BottomNavigationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : BottomNavigationView(
    context,
    attrs,
) , AbstractView.Position {

    override fun apply(position: Int) {
        if (position in 0 until menu.size()) {
            selectedItemId = menu[position].itemId
        }
    }

    override fun currentPosition(): Int {
        menu.forEachIndexed { index: Int, item: MenuItem ->
            if (item.isChecked) {
                return index
            }
        }
        return -1
    }

}