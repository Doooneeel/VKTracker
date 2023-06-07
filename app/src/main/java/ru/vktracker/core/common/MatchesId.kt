package ru.vktracker.core.common

/**
 * @author Danil Glazkov on 01.06.2023, 03:45
 */
interface MatchesId {
    fun matches(id: Int): Boolean
}