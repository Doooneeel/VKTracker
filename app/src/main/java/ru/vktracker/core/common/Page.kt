package ru.vktracker.core.common

/**
 * @author Danil Glazkov on 11.06.2023, 06:58
 */
interface Page {

    fun offset(): Int

    fun count(): Int


    data class Base(private val offset: Int, private val count: Int) : Page {

        override fun count(): Int = count

        override fun offset(): Int = offset

    }

}