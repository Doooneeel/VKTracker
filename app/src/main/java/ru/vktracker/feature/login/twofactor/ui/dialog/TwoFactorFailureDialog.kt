package ru.vktracker.feature.login.twofactor.ui.dialog

import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.vktracker.R
import ru.vktracker.core.ui.dialog.Dialog

/**
 * @author Danil Glazkov on 07.07.2023, 19:47
 */
class TwoFactorFailureDialog(
    private val message: String,
    private val onCancel: () -> Unit
) : Dialog.AbstractAlert() {

    override fun MaterialAlertDialogBuilder.setup(view: View) {
        setIcon(R.drawable.ic_error_outline)
        setTitle(R.string.two_fa_error_title)
        setMessage(message)
        setOnCancelListener { onCancel.invoke() }
        setPositiveButton(R.string.close) { dialog, _ -> dialog.cancel() }
    }
}