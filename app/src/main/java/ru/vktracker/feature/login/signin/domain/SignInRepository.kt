package ru.vktracker.feature.login.signin.domain

/**
 * @author Danil Glazkov on 03.07.2023, 2:38
 */
interface SignInRepository {

    suspend fun login(login: String, password: CharArray): SignInDomainResult

}