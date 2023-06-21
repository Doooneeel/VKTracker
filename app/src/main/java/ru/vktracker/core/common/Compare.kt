package ru.vktracker.core.common

/**
 * @author Danil Glazkov on 20.06.2023, 21:37
 */
interface Compare<T> {
    fun compare(source: T): Boolean
}