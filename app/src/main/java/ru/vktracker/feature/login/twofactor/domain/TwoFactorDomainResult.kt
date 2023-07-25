package ru.vktracker.feature.login.twofactor.domain

/**
 * @author Danil Glazkov on 26.06.2023, 19:37
 */
interface TwoFactorDomainResult {

    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {

        fun success(token: String): T

        fun failure(exception: TwoFactorException): T

    }

    class Failure(private val exception: TwoFactorException) : TwoFactorDomainResult {
        override fun <T> map(mapper: Mapper<T>): T = mapper.failure(exception)
    }

    class Success(private val token: String) : TwoFactorDomainResult {
        override fun <T> map(mapper: Mapper<T>): T = mapper.success(token)
    }

}