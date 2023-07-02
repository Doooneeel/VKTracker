package ru.vktracker.core.ui.text.validate

import android.util.Patterns.*

/**
 * @author Danil Glazkov on 22.06.2023, 17:37
 */
interface ValidateNumberPhone {
    class AndroidPattern(errorMessage: String = "") : Validate.Pattern(PHONE, errorMessage)
}