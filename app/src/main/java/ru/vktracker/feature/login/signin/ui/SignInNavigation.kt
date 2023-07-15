package ru.vktracker.feature.login.signin.ui

import ru.vktracker.R
import ru.vktracker.core.ui.navigation.Navigation
import ru.vktracker.core.ui.navigation.NavigationCommunication

/**
 * @author Danil Glazkov on 22.06.2023, 17:54
 */
interface SignInNavigation {

    interface External {
        fun navigateToWelcomeScreen()
    }

    interface Internal {

        fun navigateToTwoFactorScreen(phoneMask: String, redirectUrl: String)

        fun navigateToTabsScreen()

    }

    interface Combined : External, Internal


    class Base(private val communication: NavigationCommunication) : Combined {

        override fun navigateToTwoFactorScreen(phoneMask: String, redirectUrl: String) =
            communication.put(
                Navigation.Direction(
                    SignInFragmentDirections.actionSignInToTwoFactorAuth(phoneMask, redirectUrl)
                )
            )

        override fun navigateToWelcomeScreen() = communication.put(
            Navigation.ID(R.id.action_signIn_to_welcome)
        )

        override fun navigateToTabsScreen() = communication.put(
            Navigation.ID(R.id.action_signIn_to_tabs)
        )
    }

}