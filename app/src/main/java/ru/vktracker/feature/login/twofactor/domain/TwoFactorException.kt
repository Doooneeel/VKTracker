package ru.vktracker.feature.login.twofactor.domain

/**
 * @author Danil Glazkov on 08.07.2023, 10:01
 */
abstract class TwoFactorException : Exception() {

    class NoInternetConnection : TwoFactorException()

    class WrongCode : TwoFactorException()

}