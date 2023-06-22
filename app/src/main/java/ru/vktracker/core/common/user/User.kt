package ru.vktracker.core.common.user

import ru.vktracker.core.common.Compare

/**
 * @author Danil Glazkov on 04.06.2023, 20:59
 */
interface User : Compare<User> {

    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {
        fun map(id: Long, name: String, avatar: String): T
    }

    data class Base(
        private val id: Long,
        private val name: String,
        private val avatar: String,
    ) : User {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(id, name, avatar)

        override fun compare(source: User): Boolean =
            source.map(UserCompareMapper.Object(id, name, avatar))
    }


    object Empty : User {

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(0, "", "")

        override fun compare(source: User): Boolean = source.map(UserCompareMapper.Id(0))

    }

}