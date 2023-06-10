package ru.vktracker.core.common

/**
 * @author Danil Glazkov on 10.06.2023, 01:39
 */
interface IsTracked {
    fun isTracked(id: Long): Boolean
}