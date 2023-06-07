package ru.vktracker.core.ui

/**
 * @author Danil Glazkov on 02.06.2023, 19:01
 */
interface OnClickCallback<T> {
    fun onClick(data: T)
}