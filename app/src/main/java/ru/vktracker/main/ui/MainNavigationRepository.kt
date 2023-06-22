package ru.vktracker.main.ui

/**
 * @author Danil Glazkov on 15.06.2023, 21:20
 */
interface MainNavigationRepository {

    fun lastSelectedPosition(default: Int): Int

    fun changeSelectedPosition(position: Int)

}