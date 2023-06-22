package ru.vktracker.core.ui.text.validate

import ru.vktracker.core.ui.text.ErrorMessage

/**
 * @author Danil Glazkov on 25.07.2022, 04:21
 */
open class ValidateChain(
    private val current: Validate,
    private val next: Validate
) : Validate {
    private var currentValid = false

    override fun isValid(source: String): Boolean {
        currentValid = current.isValid(source)
        return if (currentValid) next.isValid(source) else false
    }

    override fun errorMessage(): ErrorMessage {
        return if (currentValid)
            next.errorMessage()
        else
            current.errorMessage()
    }
}
