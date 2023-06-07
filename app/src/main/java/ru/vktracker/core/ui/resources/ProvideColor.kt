package ru.vktracker.core.ui.resources

import androidx.annotation.ColorRes

/**
 * @author Danil Glazkov on 04.06.2023, 01:45
 */
interface ProvideColor {
    fun color(@ColorRes id: Int): Int
}