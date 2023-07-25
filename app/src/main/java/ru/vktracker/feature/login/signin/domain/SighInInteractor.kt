package ru.vktracker.feature.login.signin.domain

import kotlinx.coroutines.delay

/**
 * @author Danil Glazkov on 24.06.2023, 13:36
 */
interface SighInInteractor {

    suspend fun login(login: String, password: CharArray): SignInDomainResult


    class Base : SighInInteractor {
        override suspend fun login(login: String, password: CharArray): SignInDomainResult {
            //todo make signIn

            delay(2500)
            return if (true) {
                SignInDomainResult.TwoFactorAuth("", "")
            } else {
                SignInDomainResult.Failure(SignInDomainException.IncorrectLoginData())
            }
        }
    }

}