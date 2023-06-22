package ru.vktracker.feature.profile.ui

import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.vktracker.R
import ru.vktracker.core.ui.dialog.AbstractAlertDialog

/**
 * @author Danil Glazkov on 20.06.2023, 14:48
 */
class LogoutAlertDialog(
    private val onCancel: () -> Unit,
    private val onConfirm: () -> Unit,
) : AbstractAlertDialog.Abstract() {

    override val themeOverlay = R.style.ThemeOverlay_Material3_MaterialAlertDialog_IconPrimaryColor

    override fun MaterialAlertDialogBuilder.builder(): AlertDialog {
        setTitle(R.string.question_logout)
        setMessage(R.string.explanation_logout)
        setPositiveButton(R.string.logout) { dialog, _ ->
            dialog.cancel()
            onConfirm.invoke()
        }
        setIcon(R.drawable.ic_logout)

        setNegativeButton(R.string.cancel) { dialog, _ -> dialog.cancel() }
        setOnCancelListener { onCancel.invoke() }

        return create()
    }
}