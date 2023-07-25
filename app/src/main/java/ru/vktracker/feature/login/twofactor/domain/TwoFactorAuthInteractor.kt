package ru.vktracker.feature.login.twofactor.domain

import kotlinx.coroutines.delay

/**
 * @author Danil Glazkov on 26.06.2023, 19:36
 */
interface TwoFactorAuthInteractor {

    suspend fun requestCode(redirect: String)

    suspend fun confirmCode(code: String): TwoFactorDomainResult


    class Base : TwoFactorAuthInteractor {

        override suspend fun confirmCode(code: String): TwoFactorDomainResult {
            //todo make twoFactor

            delay(3000)
            return if (true) {
                TwoFactorDomainResult.Success("")
            } else {
                TwoFactorDomainResult.Failure(TwoFactorException.NoInternetConnection())
            }
        }

        override suspend fun requestCode(redirect: String) {

        }
    }

}