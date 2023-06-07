package ru.vktracker.core.common.text

/**
 * @author Danil Glazkov on 04.06.2023, 20:53
 */
interface UsernameFormat {

    fun format(firstName: String?, lastName: String?): String

    class Base : UsernameFormat {
        override fun format(firstName: String?, lastName: String?): String =
            "${ firstName ?: "" } ${ lastName ?: "" }"
    }

}