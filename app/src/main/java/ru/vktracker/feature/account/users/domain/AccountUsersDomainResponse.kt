package ru.vktracker.feature.account.users.domain

import ru.vktracker.core.common.Users
import java.lang.Exception

/**
 * @author Danil Glazkov on 10.06.2023, 02:45
 */
interface AccountUsersDomainResponse {

    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {

        fun success(users: Users): T

        fun failure(exception: Exception): T

    }

    class Success(private val users: Users): AccountUsersDomainResponse {
        override fun <T> map(mapper: Mapper<T>): T = mapper.success(users)
    }

    class Failure(private val exception: Exception): AccountUsersDomainResponse {
        override fun <T> map(mapper: Mapper<T>): T = mapper.failure(exception)
    }

}