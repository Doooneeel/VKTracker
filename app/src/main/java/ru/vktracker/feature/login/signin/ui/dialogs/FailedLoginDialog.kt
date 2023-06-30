package ru.vktracker.feature.login.signin.ui.dialogs

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.vktracker.R
import ru.vktracker.core.ui.dialog.AbstractDialog
import ru.vktracker.core.ui.dialog.DialogDisplayStrategy

/**
 * @author Danil Glazkov on 27.06.2023, 21:00
 */
class FailedLoginDialog(
    private val message: String,
    private val showing: () -> Unit,
    private val closing: () -> Unit,
) : AbstractDialog.Abstract(DialogDisplayStrategy.UntilCancel()) {

    override fun onCancel() = closing.invoke()
    override fun onShown() = showing.invoke()

    override fun MaterialAlertDialogBuilder.builder(context: Context): AlertDialog {
        setIcon(R.drawable.ic_error_outline)
        setTitle(R.string.auth_error)
        setMessage(message)
        setPositiveButton(R.string.close) { dialog, _ ->
            dialog.cancel()
        }
        return create()
    }
}