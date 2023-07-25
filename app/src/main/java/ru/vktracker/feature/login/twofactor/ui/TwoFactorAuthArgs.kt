package ru.vktracker.feature.login.twofactor.ui

import androidx.lifecycle.SavedStateHandle

/**
 * @author Danil Glazkov on 27.06.2023, 01:16
 */
interface TwoFactorAuthArgs {

    fun phoneMask(): String

    fun redirectUrl(): String


    class Base(private val savedStateHandle: SavedStateHandle) : TwoFactorAuthArgs {

        override fun phoneMask(): String = savedStateHandle["argPhoneMask"] ?: ""

        override fun redirectUrl(): String = savedStateHandle["argRedirectUrl"] ?: ""

    }
}