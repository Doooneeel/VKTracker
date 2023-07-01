package ru.vktracker.core.ui.dialog

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.vktracker.core.ui.viewmodel.Communication

/**
 * @author Danil Glazkov on 20.06.2023, 14:49
 */
interface DialogCommunication : Communication.Mutable<AbstractDialog> {

    interface Update {
        fun putDialog(dialog: AbstractDialog)
    }

    interface Observe {
        fun observeDialog(owner: LifecycleOwner, observer: Observer<AbstractDialog>)
    }

    class Base : Communication.Ui<AbstractDialog>(), DialogCommunication

}