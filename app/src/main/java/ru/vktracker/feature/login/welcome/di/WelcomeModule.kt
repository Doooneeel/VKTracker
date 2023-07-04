package ru.vktracker.feature.login.welcome.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vktracker.core.ui.navigation.NavigationCommunication

/**
 * @author Danil Glazkov on 22.06.2023, 20:23
 */
@Module
@InstallIn(ViewModelComponent::class)
class WelcomeModule {

    @Provides
    fun provideNavigationCommunication(): NavigationCommunication =
        NavigationCommunication.Base()

}