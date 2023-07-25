package ru.vktracker.core.ui.state

/**
 * @author Danil Glazkov on 10.07.2023, 14:13
 */
interface HandleUiState<T> {
    fun handle(savedState: T?): T?
}