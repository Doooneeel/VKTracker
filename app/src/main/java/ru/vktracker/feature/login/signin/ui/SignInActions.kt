package ru.vktracker.feature.login.signin.ui

/**
 * @author Danil Glazkov on 10.07.2023, 11:06
 */
interface SignInActions {

    fun changeInput(login: String, password: CharArray)

    fun login(login: String, password: CharArray)

}