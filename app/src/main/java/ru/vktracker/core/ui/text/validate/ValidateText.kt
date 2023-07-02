package ru.vktracker.core.ui.text.validate

/**
 * @author Danil Glazkov on 25.07.2022, 04:17
 */
interface ValidateText : Validate<String> {

    class MinLength(
        private val minLength: Int,
        message: String = ""
    ) : Validate.Abstract<String>(message) {
        override fun isValid(source: String) = source.length >= minLength
    }

    class NotEmpty(errorMessage: String = "") : Validate.Abstract<String>(errorMessage) {
        override fun isValid(source: String) = source.isNotEmpty() && source.isNotBlank()
    }

}