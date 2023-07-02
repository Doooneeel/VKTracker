package ru.vktracker.core.ui.text.validate

import ru.vktracker.core.ui.text.Message

/**
 * @author Danil Glazkov on 22.06.2023, 17:31
 */
interface Validate<T> {

    fun isValid(source: T): Boolean

    fun errorMessage(): Message


    abstract class Abstract<T>(private val errorMessage: String = "") : Validate<T> {
        override fun errorMessage() = Message.Base(errorMessage)
    }

    abstract class Pattern(
        private val pattern: java.util.regex.Pattern,
        errorMessage: String,
    ) : Abstract<String>(errorMessage) {
        override fun isValid(source: String): Boolean =
            pattern.matcher(source).matches()
    }
}