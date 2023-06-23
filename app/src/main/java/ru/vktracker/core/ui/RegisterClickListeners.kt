package ru.vktracker.core.ui

import androidx.viewbinding.ViewBinding

/**
 * @author Danil Glazkov on 23.06.2023, 17:46
 */
interface RegisterClickListeners<VB : ViewBinding> {
    fun VB.registerClickListeners(throttle: Throttle) = Unit
}