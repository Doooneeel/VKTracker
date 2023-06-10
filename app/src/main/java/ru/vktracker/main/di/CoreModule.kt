package ru.vktracker.main.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.common.CurrentTime
import ru.vktracker.core.common.text.UsernameFormat
import ru.vktracker.core.ui.AndroidCurrentTime
import ru.vktracker.core.ui.HandleUiError
import ru.vktracker.core.ui.resources.ManageResources
import java.util.Locale
import javax.inject.Singleton

/**
 * @author Danil Glazkov on 02.06.2023, 03:05
 */
@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    @Provides
    @Singleton
    fun provideResourceManager(@ApplicationContext context: Context): ManageResources =
        ManageResources.Base(context)

    @Provides
    @Singleton
    fun provideUsernameFormat(): UsernameFormat = UsernameFormat.Base()

    @Provides
    @Singleton
    fun provideLocale(): Locale = Locale.getDefault()

    @Provides
    @Singleton
    fun provideCurrentTime(): CurrentTime = AndroidCurrentTime()

    @Provides
    @Singleton
    fun provideHandleUiError(): HandleUiError = HandleUiError.Base()

    @Provides
    @Singleton
    fun provideDispatchers(): CoroutineDispatchers = CoroutineDispatchers.Base()
}