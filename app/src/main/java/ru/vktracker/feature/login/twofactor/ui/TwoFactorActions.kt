package ru.vktracker.feature.login.twofactor.ui

/**
 * @author Danil Glazkov on 10.07.2023, 15:18
 */
interface TwoFactorActions {

    fun changeConfirmationCodeState(isComplete: Boolean)

    fun confirmCode(code: String)

    fun resendCode()

}