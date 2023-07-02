package ru.vktracker.core.ui.dialog

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.vktracker.R
import ru.vktracker.core.ui.SingleLifecycleEventObserver

/**
 * @author Danil Glazkov on 20.06.2023, 14:47
 */
interface AbstractDialog {

    fun show(context: Context, lifecycle: Lifecycle)


    abstract class Abstract(private val strategy: DialogDisplayStrategy) : AbstractDialog {
        protected open val themeOverlay =
            R.style.ThemeOverlay_Material3_MaterialAlertDialog_IconPrimaryColor

        protected abstract fun MaterialAlertDialogBuilder.builder(context: Context): AlertDialog

        protected open fun onCancel() = Unit
        protected open fun onDismiss() = Unit
        protected open fun onShown() = Unit

        override fun show(context: Context, lifecycle: Lifecycle) {
            if (!strategy.needToShow()) {
                return
            }
            val dialog: AlertDialog = MaterialAlertDialogBuilder(context, themeOverlay)
                .builder(context)

            dialog.setOnCancelListener {
                strategy.dialogCanceled()
                onCancel()
            }
            dialog.setOnDismissListener {
                strategy.dialogDismissed()
                onDismiss()
            }

            lifecycle.addObserver(
                SingleLifecycleEventObserver.Pause { dialog.dismiss() }
            )

            lifecycle.addObserver(
                SingleLifecycleEventObserver.Create {
                    if (strategy.needToShow()) {
                        strategy.dialogShown()
                        onShown()
                        dialog.show()
                    }
                }
            )
        }
    }
}