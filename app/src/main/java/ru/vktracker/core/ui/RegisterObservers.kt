package ru.vktracker.core.ui

/**
 * @author Danil Glazkov on 23.06.2023, 17:49
 */
interface RegisterObservers<VM : BaseViewModel> {
    fun VM.registerObservers() = Unit
}