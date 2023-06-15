package ru.vktracker.main.ui

/**
 * @author Danil Glazkov on 15.06.2023, 21:20
 */
interface MainNavigationRepository {

    fun lastScreenIndex(default: Int): Int

    fun changeLastScreen(index: Int)

}