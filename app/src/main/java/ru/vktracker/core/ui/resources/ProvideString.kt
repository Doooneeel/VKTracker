package ru.vktracker.core.ui.resources

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

/**
 * @author Danil Glazkov on 04.06.2023, 01:39
 */
interface ProvideString {

    interface Single {
        fun string(@StringRes id: Int): String
    }

    interface Array {
        fun stringList(id: Int): List<String>
    }

    interface Quantity {
        fun quantityString(@PluralsRes id: Int, quantity: Int, vararg formatArgs: Any)
    }

    interface Combine : Single, Quantity, Array

}