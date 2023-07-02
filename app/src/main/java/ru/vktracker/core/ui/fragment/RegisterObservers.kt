package ru.vktracker.core.ui.fragment

import ru.vktracker.core.ui.viewmodel.BaseViewModel

/**
 * @author Danil Glazkov on 23.06.2023, 17:49
 */
interface RegisterObservers<VM : BaseViewModel> {
    fun VM.registerObservers()
}