package ru.vktracker.core.common.text

/**
 * @author Danil Glazkov on 07.07.2023, 14:39
 */
interface TimeTextFormat {
    fun format(millis: Long): String
}