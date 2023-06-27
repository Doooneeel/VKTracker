package ru.vktracker.feature.login.signin.domain

import java.lang.Exception

/**
 * @author Danil Glazkov on 24.06.2023, 14:16
 */
interface SignInDomainResponse {

    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {

        fun success(token: CharArray): T

        fun failure(exception: Exception): T

        fun mapTwoFactorAuth(phoneMask: String, redirectUrl: String): T

    }


    class Success (private val token: CharArray) : SignInDomainResponse {
        override fun <T> map(mapper: Mapper<T>): T = mapper.success(token)
    }

    class Failure (private val exception: Exception) : SignInDomainResponse {
        override fun <T> map(mapper: Mapper<T>): T = mapper.failure(exception)
    }

    class TwoFactorAuth(
        private val phoneMask: String,
        private val redirectUrl: String
    ) : SignInDomainResponse {
        override fun <T> map(mapper: Mapper<T>): T =
            mapper.mapTwoFactorAuth(phoneMask, redirectUrl)
    }

}