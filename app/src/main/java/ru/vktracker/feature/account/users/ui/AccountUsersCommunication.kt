package ru.vktracker.feature.account.users.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.vktracker.core.ui.Communication

/**
 * @author Danil Glazkov on 02.06.2023, 13:22
 */
interface AccountUsersCommunication : Communication.Mutable<AccountUsersUi> {

    interface Observe {
        fun observeAccountUsers(owner: LifecycleOwner, observer: Observer<AccountUsersUi>)
    }

    class Base : Communication.Ui<AccountUsersUi>(), AccountUsersCommunication

}