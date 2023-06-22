package ru.vktracker.core.ui.text.validate

import ru.vktracker.core.ui.text.ErrorMessage

/**
 * @author Danil Glazkov on 22.06.2023, 17:31
 */
interface Validate {

    fun isValid(source: String): Boolean

    fun errorMessage(): ErrorMessage


    abstract class Abstract(private val errorMessage: String) : Validate {
        override fun errorMessage() = ErrorMessage.Base(errorMessage)
    }

    abstract class Pattern(
        private val pattern: java.util.regex.Pattern,
        errorMessage: String
    ) : Abstract(errorMessage) {
        override fun isValid(source: String): Boolean =
            pattern.matcher(source).matches()
    }
}