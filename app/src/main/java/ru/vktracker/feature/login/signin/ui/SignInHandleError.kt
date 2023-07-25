package ru.vktracker.feature.login.signin.ui

import ru.vktracker.R
import ru.vktracker.core.ui.HandleError
import ru.vktracker.core.ui.resources.ProvideString
import ru.vktracker.feature.login.signin.domain.SignInDomainException
import ru.vktracker.feature.login.signin.domain.SignInDomainException.*

/**
 * @author Danil Glazkov on 27.06.2023, 22:51
 */
class SignInHandleError(
    private val resources: ProvideString.Single,
) : HandleError<SignInDomainException, String> {
    override fun handle(exception: SignInDomainException): String = resources.string(
        when (exception) {
            is IncorrectLoginData -> R.string.incorrect_login_or_password
            is NoInternetConnectionException -> R.string.internet_connection_error
            else -> R.string.unknown_error
        }
    )
}