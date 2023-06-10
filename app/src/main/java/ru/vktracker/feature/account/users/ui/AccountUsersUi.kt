package ru.vktracker.feature.account.users.ui

import ru.vktracker.core.ui.view.AbstractView

/**
 * @author Danil Glazkov on 02.06.2023, 13:20
 */
interface AccountUsersUi {

    fun apply(view: AbstractView.List<AccountUserUi>)


    abstract class Abstract(private val friends: List<AccountUserUi>) : AccountUsersUi {
        override fun apply(view: AbstractView.List<AccountUserUi>) = view.apply(friends)
    }

    object Empty : Abstract(emptyList())

    data class Base(private val friends: List<AccountUserUi>) : Abstract(friends)

    data class Header(private val text: String) : Abstract(listOf(AccountUserUi.Header(text)))

    data class Message(private val text: String) : Abstract(listOf(AccountUserUi.Message(text)))
}