package ru.vktracker.feature.login.signin.domain

import java.lang.Exception

/**
 * @author Danil Glazkov on 24.06.2023, 13:36
 */
interface SighInInteractor {

    suspend fun login(login: CharArray, password: CharArray): SignInDomainResponse


    class Base : SighInInteractor {
        override suspend fun login(login: CharArray, password: CharArray): SignInDomainResponse {
            return SignInDomainResponse.Failure(Exception())
        }
    }

}