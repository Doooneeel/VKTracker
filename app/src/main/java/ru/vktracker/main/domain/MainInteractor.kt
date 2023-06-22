package ru.vktracker.main.domain

/**
 * @author Danil Glazkov on 21.06.2023, 14:50
 */
interface MainInteractor {

    fun isAuth(): Boolean


    class Base : MainInteractor {
        override fun isAuth(): Boolean {
            //todo check auth status
            return true
        }
    }

}