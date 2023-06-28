package ru.vktracker.feature.login.signin.ui.validate

import ru.vktracker.core.ui.text.validate.Validate

/**
 * @author Danil Glazkov on 25.06.2023, 16:19
 */
interface SignInValidateInput {

    fun isValid(login: String, password: CharArray): Boolean


    class Base(
        private val validateLogin: Validate<String>,
        private val validatePassword: Validate<CharArray>,
    ) : SignInValidateInput {
        override fun isValid(login: String, password: CharArray): Boolean =
            validateLogin.isValid(login) && validatePassword.isValid(password)
    }

}