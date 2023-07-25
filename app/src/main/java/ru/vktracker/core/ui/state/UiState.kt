package ru.vktracker.core.ui.state

import android.os.Parcelable
import androidx.viewbinding.ViewBinding

/**
 * @author Danil Glazkov on 09.07.2023, 21:46
 */
interface UiState<T> : Parcelable {

    fun update(view: T)


    abstract class AbstractNothing<T : ViewBinding> : UiState<T> {
        override fun update(view: T) = Unit
    }

    abstract class AbstractDialog<T>(
        private val openDialogUiState: UiState<T>,
        private val closingDialogUiState: UiState<T>,
        private val dialogCancelState: DialogCancelState,
    ) : UiState<T> {

        protected abstract fun showDialog(view: T)

        protected fun dialogCanceled(view: T) {
            closingDialogUiState.update(view)
            dialogCancelState.cancel()
        }

        override fun update(view: T) = if (dialogCancelState.isCancel()) {
            closingDialogUiState.update(view)
        } else {
            openDialogUiState.update(view)
            showDialog(view)
        }
    }

}