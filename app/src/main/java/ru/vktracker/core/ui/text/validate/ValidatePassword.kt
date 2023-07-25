package ru.vktracker.core.ui.text.validate

/**
 * @author Danil Glazkov on 28.06.2023, 23:25
 */
interface ValidatePassword : Validate<CharArray>  {

    class MinLength(
        private val minLength: Int,
        message: String = ""
    ) : Validate.Abstract<CharArray>(message), ValidatePassword {
        override fun isValid(source: CharArray) = source.size >= minLength
    }

    class NotEmpty(message: String = "") : Validate.Abstract<CharArray>(message), ValidatePassword {
        override fun isValid(source: CharArray): Boolean =
            source.count { it != ' ' && it != Char.MIN_VALUE } > 0
    }
}