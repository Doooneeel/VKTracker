package ru.vktracker.core.ui.view.common.listeners

import android.view.KeyEvent
import android.view.View

/**
 * @author Danil Glazkov on 29.06.2023, 03:55
 */
interface SingleOnKeyListener : View.OnKeyListener {

    abstract class Abstract(
        private val eventCode: Int,
        private val action: (View) -> Unit
    ) : SingleOnKeyListener {
        override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == eventCode) {
                action.invoke(v)
                return true
            }
            return false
        }
    }

    class Enter(action: (View) -> Unit) : Abstract(KeyEvent.KEYCODE_ENTER, action)
}