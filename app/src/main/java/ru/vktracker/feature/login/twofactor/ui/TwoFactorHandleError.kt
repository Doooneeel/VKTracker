package ru.vktracker.feature.login.twofactor.ui

import ru.vktracker.R
import ru.vktracker.core.ui.HandleError
import ru.vktracker.core.ui.resources.ProvideString
import ru.vktracker.feature.login.twofactor.domain.TwoFactorException

/**
 * @author Danil Glazkov on 07.07.2023, 18:27
 */
interface TwoFactorHandleError : HandleError<TwoFactorException, String> {

    class Base(private val provideString: ProvideString.Single) : TwoFactorHandleError {
        override fun handle(exception: TwoFactorException): String = provideString.string(
            when (exception) {
                is TwoFactorException.NoInternetConnection -> R.string.internet_connection_error
                is TwoFactorException.WrongCode -> R.string.wrong_code
                else -> R.string.unknown_error
            }
        )
    }
}