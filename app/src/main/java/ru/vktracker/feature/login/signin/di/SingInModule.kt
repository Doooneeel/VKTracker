package ru.vktracker.feature.login.signin.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vktracker.core.ui.navigation.ScreenCommunication
import ru.vktracker.feature.login.signin.ui.SignInNavigation
import javax.inject.Qualifier

/**
 * @author Danil Glazkov on 22.06.2023, 19:05
 */
@Module
@InstallIn(ViewModelComponent::class)
class SingInModule {

    @Qualifier
    annotation class ModuleQualifier

    private val screenCommunication = ScreenCommunication.Base()

    @Provides
    fun provideSignInNavigation(): SignInNavigation = SignInNavigation.Base(screenCommunication)

    @Provides
    @ModuleQualifier
    fun provideScreenCommunication(): ScreenCommunication = screenCommunication

}