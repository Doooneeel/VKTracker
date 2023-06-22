package ru.vktracker.core.common.user

/**
 * @author Danil Glazkov on 04.06.2023, 20:59
 */
interface User {

    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {
        fun map(id: Long, name: String, avatar: String): T
    }

    data class Base(
        private val id: Long,
        private val name: String,
        private val avatar: String,
    ) : User {
        override fun <T> map(mapper: Mapper<T>): T =
            mapper.map(id, name, avatar)
    }

    object Empty : User {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(0, "", "")
    }

}