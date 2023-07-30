package ru.vktracker.feature.profile.ui.dialog

import ru.vktracker.core.ui.dialog.GenericDialog

/**
 * @author Danil Glazkov on 20.06.2023, 14:48
 */
interface LogoutDialog : GenericDialog {

    interface Show {
        fun showLogoutDialog()
    }

    class Base : GenericDialog.AbstractAlert(LogoutDialogFragment()), LogoutDialog

}