package ru.vktracker.core.ui.text.validate

import android.util.Patterns.*

/**
 * @author Danil Glazkov on 22.06.2023, 17:41
 */
interface ValidateEmail : Validate<String> {

    class AndroidPattern(errorMessage: String = "") :
        Validate.Pattern(EMAIL_ADDRESS, errorMessage), ValidateEmail
}