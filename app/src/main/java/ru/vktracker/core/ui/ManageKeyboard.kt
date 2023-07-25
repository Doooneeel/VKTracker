package ru.vktracker.core.ui

import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * @author Danil Glazkov on 27.06.2023, 18:25
 */
interface ManageKeyboard {

    fun showKeyboard(view: View)

    fun hideKeyboard(view: View)


    object Base : ManageKeyboard {
        private val service = InputMethodManager::class.java

        override fun hideKeyboard(view: View) {
            view.context?.getSystemService(service)
                ?.hideSoftInputFromWindow(view.windowToken, 0)
        }

        override fun showKeyboard(view: View) {
            view.context?.getSystemService(service)?.showSoftInput(view, 0)
        }
    }

}