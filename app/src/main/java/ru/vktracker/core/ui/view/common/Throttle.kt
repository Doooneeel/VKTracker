package ru.vktracker.core.ui.view.common

/**
 * @author Danil Glazkov on 13.06.2023, 10:54
 */
interface Throttle {

    fun update(time: Long)

    fun lastTime(): Long


    class Base(private var lastTime: Long = 0L) : Throttle {

        override fun update(time: Long) { lastTime = time }

        override fun lastTime(): Long = lastTime

    }
}
