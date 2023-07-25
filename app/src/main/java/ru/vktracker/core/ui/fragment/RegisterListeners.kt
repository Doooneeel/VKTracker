package ru.vktracker.core.ui.fragment

import androidx.viewbinding.ViewBinding

/**
 * @author Danil Glazkov on 23.06.2023, 17:46
 */
interface RegisterListeners<VB : ViewBinding> {
    fun VB.registerListeners()
}