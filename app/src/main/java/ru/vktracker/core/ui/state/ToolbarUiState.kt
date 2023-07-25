package ru.vktracker.core.ui.state

import com.google.android.material.appbar.MaterialToolbar
import kotlinx.parcelize.Parcelize
import ru.vktracker.R

/**
 * @author Danil Glazkov on 15.07.2023, 16:43
 */
interface ToolbarUiState : UiState<MaterialToolbar> {

    @Parcelize
    object HideNavIcon : ToolbarUiState {
        override fun update(view: MaterialToolbar) { view.navigationIcon = null }
    }

    @Parcelize
    object ShowNavIcon : ToolbarUiState {
        override fun update(view: MaterialToolbar) {
            view.setNavigationIcon(R.drawable.ic_arrow_back_tiny)
        }
    }

    @Parcelize
    object Unit : ToolbarUiState {
        override fun update(view: MaterialToolbar) = kotlin.Unit
    }

}