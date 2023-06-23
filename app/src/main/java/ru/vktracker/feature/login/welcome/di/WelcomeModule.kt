package ru.vktracker.feature.login.welcome.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vktracker.core.ui.navigation.ScreenCommunication
import javax.inject.Qualifier

/**
 * @author Danil Glazkov on 22.06.2023, 20:23
 */
@Module
@InstallIn(ViewModelComponent::class)
class WelcomeModule {

    @Qualifier
    annotation class ModuleQualifier

    @Provides
    @ModuleQualifier
    fun provideScreenCommunication(): ScreenCommunication = ScreenCommunication.Base()

}