package ru.vktracker.main.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vktracker.core.ui.resources.ManageResources
import ru.vktracker.data.core.cache.PreferencesDataStore
import ru.vktracker.data.main.BaseMainNavigationRepository
import ru.vktracker.main.ui.MenuItemCommunication
import ru.vktracker.main.ui.MainNavigationRepository

/**
 * @author Danil Glazkov on 15.06.2023, 21:50
 */
@Module
@InstallIn(ViewModelComponent::class)
class MainModule {

    @Provides
    fun provideCommunication(): MenuItemCommunication {
        return MenuItemCommunication.Base()
    }

    @Provides
    fun provideMainNavigationRepository(resources: ManageResources): MainNavigationRepository {
        return BaseMainNavigationRepository(
            PreferencesDataStore.INT(
                resources.preferences(PREFERENCES_NAME)
            )
        )
    }

    companion object {
        private const val PREFERENCES_NAME = "mainNavigationSharedPreferences"
    }

}