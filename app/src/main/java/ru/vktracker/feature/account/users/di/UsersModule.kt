package ru.vktracker.feature.account.users.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vktracker.feature.account.users.domain.AccountUsersRepository
import ru.vktracker.feature.account.users.ui.mappers.UserToUiMapper
import javax.inject.Singleton

/**
 * @author Danil Glazkov on 10.06.2023, 03:30
 */
@Module
@InstallIn(SingletonComponent::class)
class UsersModule {

    @Singleton
    @Provides
    fun provideUserToUiMapper(repository: AccountUsersRepository): UserToUiMapper =
        UserToUiMapper.Base(repository)

    @Singleton
    @Provides
    fun provideFavesRepository(): AccountUsersRepository {
        TODO()
    }
}