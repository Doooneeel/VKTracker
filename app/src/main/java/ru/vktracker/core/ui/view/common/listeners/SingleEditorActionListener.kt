package ru.vktracker.core.ui.view.common.listeners

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView

/**
 * @author Danil Glazkov on 10.07.2023, 13:00
 */
interface SingleEditorActionListener : TextView.OnEditorActionListener {

    abstract class Abstract(
        private val action: Int,
        private val block: () -> Unit
    ) : SingleEditorActionListener {
        override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
            return if (actionId == action) {
                block.invoke()
                true
            } else
                false
        }
    }

    class Done (block: () -> Unit) : Abstract(EditorInfo.IME_ACTION_DONE, block)

}