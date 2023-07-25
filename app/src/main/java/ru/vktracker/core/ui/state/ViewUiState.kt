package ru.vktracker.core.ui.state

import android.view.View
import kotlinx.parcelize.Parcelize

/**
 * @author Danil Glazkov on 15.07.2023, 16:43
 */
interface ViewUiState : UiState<View> {

    @Parcelize
    class Focus(private val hasFocus: Boolean) : ViewUiState {
        override fun update(view: View) {
            if (hasFocus) view.requestFocus() else view.clearFocus()
        }
    }


    @Parcelize
    object Enable : ViewUiState {
        override fun update(view: View) { view.isEnabled = true }
    }

    @Parcelize
    object Disable : ViewUiState {
        override fun update(view: View) { view.isEnabled = false }
    }


    @Parcelize
    object Unit : ViewUiState {
        override fun update(view: View) = kotlin.Unit
    }

}