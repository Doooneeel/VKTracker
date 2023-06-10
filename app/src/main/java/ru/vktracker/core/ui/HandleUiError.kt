package ru.vktracker.core.ui

import java.lang.Exception

/**
 * @author Danil Glazkov on 10.06.2023, 08:40
 */
interface HandleUiError {

    fun handle(exception: Exception): String


    class Base : HandleUiError {
        override fun handle(exception: Exception): String = "unknown error"
    }
}