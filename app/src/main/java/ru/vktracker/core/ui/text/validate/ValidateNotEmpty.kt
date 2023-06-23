package ru.vktracker.core.ui.text.validate

/**
 * @author Danil Glazkov on 25.07.2022, 09:38
 */
class ValidateNotEmpty(errorMessage: String) : Validate.Abstract(errorMessage) {
    override fun isValid(source: String) = source.isNotEmpty() && source.isNotBlank()
}