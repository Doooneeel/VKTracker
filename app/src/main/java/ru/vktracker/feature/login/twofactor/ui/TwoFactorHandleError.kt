package ru.vktracker.feature.login.twofactor.ui

import ru.vktracker.core.ui.HandleError
import java.lang.Exception

/**
 * @author Danil Glazkov on 07.07.2023, 18:27
 */
interface TwoFactorHandleError : HandleError<String> {

    class Base : TwoFactorHandleError {
        override fun handle(exception: Exception): String = "todo handleError"
    }
}