package ru.vktracker.core.ui

import java.util.concurrent.atomic.AtomicLong

/**
 * @author Danil Glazkov on 13.06.2023, 10:54
 */
interface ClickTime {

    fun registerClick(time: Long)

    fun lastClick(): Long


    class Base : ClickTime {

        private var lastClickTime = AtomicLong(0)

        override fun registerClick(time: Long) { lastClickTime.set(time) }

        override fun lastClick(): Long = lastClickTime.get()

    }
}