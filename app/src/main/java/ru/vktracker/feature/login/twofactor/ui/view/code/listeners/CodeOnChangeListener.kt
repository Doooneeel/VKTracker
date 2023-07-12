package ru.vktracker.feature.login.twofactor.ui.view.code.listeners

import android.view.View

/**
 * @author Danil Glazkov on 06.07.2023, 10:30
 */
fun interface CodeOnChangeListener {

    fun onCodeChange(code: String, isComplete: Boolean)


    object Unit : CodeOnChangeListener {
        override fun onCodeChange(code: String, isComplete: Boolean) = kotlin.Unit
    }

    class Focused(
        private val view: View,
        private val block: (String, Boolean) -> kotlin.Unit
    ) : CodeOnChangeListener {
        override fun onCodeChange(code: String, isComplete: Boolean) {
            if (view.hasFocus()) block.invoke(code, isComplete)
        }
    }

}