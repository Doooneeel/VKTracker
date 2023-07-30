package ru.vktracker.feature.profile.ui.navigation

import ru.vktracker.R
import ru.vktracker.core.ui.viewmodel.Communication
import ru.vktracker.core.ui.navigation.Navigation

/**
 * @author Danil Glazkov on 21.06.2023, 14:54
 */
interface ProfileNavigation {

    interface Internal {
        fun navigateToWelcome()
    }

    interface External {

        fun navigateToSettings()

        fun navigateToStatistics()

        fun navigateToAboutApp()

    }

    interface Combined : Internal, External


    class Base(
        private val childNavigationCommunication: Communication.Update<Navigation>,
        private val mainNavigationCommunication: Communication.Update<Navigation>
    ) : Combined {

        override fun navigateToWelcome() = mainNavigationCommunication.put(
            Navigation.ID(R.id.action_tabs_to_welcome)
        )

        override fun navigateToSettings() = childNavigationCommunication.put(
            Navigation.ID(R.id.action_profile_to_settings)
        )

        override fun navigateToStatistics() = childNavigationCommunication.put(
            Navigation.ID(R.id.action_profile_to_statistics)
        )

        override fun navigateToAboutApp() = childNavigationCommunication.put(
            Navigation.ID(R.id.action_profile_to_about_app)
        )

    }
}