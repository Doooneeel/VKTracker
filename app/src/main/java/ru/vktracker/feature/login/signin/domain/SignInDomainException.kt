package ru.vktracker.feature.login.signin.domain

/**
 * @author Danil Glazkov on 27.06.2023, 22:58
 */
abstract class SignInDomainException : Exception() {

    class NoInternetConnectionException : SignInDomainException()

    class IncorrectLoginData : SignInDomainException()

}