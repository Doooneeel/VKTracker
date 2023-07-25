package ru.vktracker.feature.login.signin.domain

/**
 * @author Danil Glazkov on 24.06.2023, 14:16
 */
interface SignInDomainResult {

    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {

        fun success(token: CharArray): T

        fun failure(exception: SignInDomainException): T

        fun mapTwoFactorAuth(phoneMask: String, redirectUrl: String): T

    }


    class Success (private val token: CharArray) : SignInDomainResult {
        override fun <T> map(mapper: Mapper<T>): T = mapper.success(token)
    }

    class Failure (private val exception: SignInDomainException) : SignInDomainResult {
        override fun <T> map(mapper: Mapper<T>): T = mapper.failure(exception)
    }

    class TwoFactorAuth(
        private val phoneMask: String,
        private val redirectUrl: String
    ) : SignInDomainResult {
        override fun <T> map(mapper: Mapper<T>): T =
            mapper.mapTwoFactorAuth(phoneMask, redirectUrl)
    }

}