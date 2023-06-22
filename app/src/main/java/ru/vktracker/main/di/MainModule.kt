package ru.vktracker.main.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vktracker.core.ui.navigation.LastPositionCommunication
import ru.vktracker.core.ui.navigation.ScreenCommunication
import ru.vktracker.core.ui.resources.ManageResources
import ru.vktracker.data.core.cache.PreferencesDataStore
import ru.vktracker.data.main.BaseMainNavigationRepository
import ru.vktracker.main.domain.MainInteractor
import ru.vktracker.main.ui.MainNavigationRepository
import javax.inject.Qualifier

/**
 * @author Danil Glazkov on 15.06.2023, 21:50
 */
@Module
@InstallIn(ViewModelComponent::class)
class MainModule {

    @Qualifier
    annotation class ModuleQualifier

    @Provides
    @ModuleQualifier
    fun provideCommunication(): LastPositionCommunication = LastPositionCommunication.Base()

    @Provides
    fun provideMainNavigationRepository(resources: ManageResources): MainNavigationRepository {
        return BaseMainNavigationRepository(
            PreferencesDataStore.INT(
                resources.preferences(PREFERENCES_NAME)
            )
        )
    }

    @Provides
    @ModuleQualifier
    fun provideScreenCommunication(): ScreenCommunication = ScreenCommunication.Base()

    @Provides
    fun provideMainInteractor(): MainInteractor = MainInteractor.Base()

    companion object {
        private const val PREFERENCES_NAME = "mainNavigationSharedPreferences"
    }

}