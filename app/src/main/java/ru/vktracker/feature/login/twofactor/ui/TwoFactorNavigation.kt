package ru.vktracker.feature.login.twofactor.ui

import ru.vktracker.R
import ru.vktracker.core.ui.navigation.Navigation
import ru.vktracker.core.ui.navigation.NavigationCommunication

/**
 * @author Danil Glazkov on 25.06.2023, 18:07
 */
interface TwoFactorNavigation {

    interface Fragment {
        fun navigateToSignIn()
    }

    interface ViewModel {
        fun navigateToTabsFragment(token: String)
    }

    interface Combine : Fragment, ViewModel


    class Base(private val navigationCommunication: NavigationCommunication) : Combine {

        override fun navigateToSignIn() = navigationCommunication.put(
            Navigation.ID(R.id.action_two_factor_to_signIn)
        )

        override fun navigateToTabsFragment(token: String) = navigationCommunication.put(
            Navigation.Direction(TwoFactorAuthFragmentDirections.actionTwoFactorAuthToTabs(token))
        )

    }

}