package ru.vktracker.core.ui.dialog

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.vktracker.core.ui.SingleLifecycleEventObserver

/**
 * @author Danil Glazkov on 20.06.2023, 14:47
 */
interface AbstractAlertDialog {

    fun show(context: Context, lifecycle: Lifecycle)


    abstract class Abstract : AbstractAlertDialog {

        protected open val themeOverlay = 0
        protected abstract fun MaterialAlertDialogBuilder.builder(): AlertDialog

        override fun show(context: Context, lifecycle: Lifecycle) {
            val dialog: AlertDialog = MaterialAlertDialogBuilder(context, themeOverlay)
                .builder()

            lifecycle.addObserver(SingleLifecycleEventObserver.Pause {
                dialog.dismiss()
            })

            dialog.show()
        }
    }
}