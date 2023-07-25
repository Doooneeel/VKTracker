package ru.vktracker.core.ui.state

import com.google.android.material.progressindicator.BaseProgressIndicator
import kotlinx.parcelize.Parcelize

/**
 * @author Danil Glazkov on 15.07.2023, 16:42
 */
interface ProgressUiState : UiState<BaseProgressIndicator<*>> {

    @Parcelize
    object Show : ProgressUiState {
        override fun update(view: BaseProgressIndicator<*>) = view.show()
    }

    @Parcelize
    object Hide : ProgressUiState {
        override fun update(view: BaseProgressIndicator<*>) = view.hide()
    }

    @Parcelize
    object Unit : ProgressUiState {
        override fun update(view: BaseProgressIndicator<*>) = kotlin.Unit
    }

}