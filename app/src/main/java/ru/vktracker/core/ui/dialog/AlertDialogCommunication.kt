package ru.vktracker.core.ui.dialog

import ru.vktracker.core.ui.Communication

/**
 * @author Danil Glazkov on 20.06.2023, 14:49
 */
interface AlertDialogCommunication : Communication.Mutable<AbstractAlertDialog> {
    class Base : Communication.SingleUi<AbstractAlertDialog>(), AlertDialogCommunication
}