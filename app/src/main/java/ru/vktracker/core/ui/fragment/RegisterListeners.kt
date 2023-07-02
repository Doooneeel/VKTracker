package ru.vktracker.core.ui.fragment

import androidx.viewbinding.ViewBinding
import ru.vktracker.core.ui.view.common.Throttle

/**
 * @author Danil Glazkov on 23.06.2023, 17:46
 */
interface RegisterListeners<VB : ViewBinding> {
    fun VB.registerListeners(throttle: Throttle) = Unit
}