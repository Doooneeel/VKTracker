package ru.vktracker.feature.profile.ui.nabigation

import ru.vktracker.R
import ru.vktracker.core.ui.Communication
import ru.vktracker.core.ui.navigation.Screen

/**
 * @author Danil Glazkov on 21.06.2023, 14:54
 */
interface ProfileNavigation {

    fun navigateToSettings()

    fun navigateToStatistics()

    fun navigateToAboutApp()


    class Base(private val communication: Communication.Update<Screen>) : ProfileNavigation {

        private object SettingsScreen : Screen.Abstract(R.id.action_profile_to_settings)
        private object AboutAppScreen : Screen.Abstract(R.id.action_profile_to_about_app)
        private object StatisticsScreen : Screen.Abstract(R.id.action_profile_to_statistics)


        override fun navigateToSettings() = communication.put(SettingsScreen)

        override fun navigateToStatistics() = communication.put(StatisticsScreen)

        override fun navigateToAboutApp() = communication.put(AboutAppScreen)

    }
}