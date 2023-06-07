package ru.vktracker.core.ui.resources

import androidx.annotation.IntegerRes

/**
 * @author Danil Glazkov on 04.06.2023, 01:42
 */
interface ProvideNumber {
    fun number(@IntegerRes id: Int): Int
}