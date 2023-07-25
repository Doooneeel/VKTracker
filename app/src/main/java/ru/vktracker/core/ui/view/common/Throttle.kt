package ru.vktracker.core.ui.view.common

/**
 * @author Danil Glazkov on 13.06.2023, 10:54
 */
interface Throttle {

    fun update(time: Long = System.currentTimeMillis())

    fun lastTime(): Long


    class Base : Throttle {

        private var lastTime: Long = 0L

        override fun update(time: Long) { lastTime = time }

        override fun lastTime(): Long = lastTime

    }
}
