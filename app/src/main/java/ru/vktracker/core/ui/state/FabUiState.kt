package ru.vktracker.core.ui.state

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import kotlinx.parcelize.Parcelize

/**
 * @author Danil Glazkov on 15.07.2023, 16:42
 */
interface FabUiState : UiState<ExtendedFloatingActionButton> {

    @Parcelize
    object Hide : FabUiState {
        override fun update(view: ExtendedFloatingActionButton) {
            view.isClickable = false
            view.hide()
        }
    }

    @Parcelize
    object Show : FabUiState {
        override fun update(view: ExtendedFloatingActionButton) {
            view.isClickable = true
            view.show()
        }
    }

    @Parcelize
    object Unit : FabUiState {
        override fun update(view: ExtendedFloatingActionButton) = kotlin.Unit
    }

}