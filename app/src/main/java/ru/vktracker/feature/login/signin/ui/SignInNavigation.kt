package ru.vktracker.feature.login.signin.ui

import ru.vktracker.R
import ru.vktracker.core.ui.navigation.Screen
import ru.vktracker.core.ui.navigation.ScreenCommunication

/**
 * @author Danil Glazkov on 22.06.2023, 17:54
 */
interface SignInNavigation {

    fun navigateToTwoFactorAuthScreen()

    fun navigateToWelcomeScreen()

    fun navigateToTabsScreen()


    class Base(private val communication: ScreenCommunication) : SignInNavigation {

        override fun navigateToTwoFactorAuthScreen() = communication.put(
            Screen.Base(R.id.action_signIn_to_twoFactorAuth)
        )

        override fun navigateToWelcomeScreen() = communication.put(
            Screen.Base(R.id.action_signIn_to_welcome)
        )

        override fun navigateToTabsScreen() = communication.put(
            Screen.Base(R.id.action_signIn_to_tabs)
        )
    }

}