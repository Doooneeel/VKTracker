package ru.vktracker.core.ui.diffutil

/**
 * @author Danil Glazkov on 11.06.2023, 05:15
 */
interface Same<T> {

    fun same(data: T): Boolean

    fun sameContent(data: T): Boolean

}