package ru.vktracker.core.common

/**
 * @author Danil Glazkov on 04.06.2023, 21:02
 */
interface Users : AsList<User> {

    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {
        fun map(users: List<User>): T
    }

    data class Base(private val users: List<User>) : Users {

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(users)

        override fun asList(): List<User> = users

    }

}