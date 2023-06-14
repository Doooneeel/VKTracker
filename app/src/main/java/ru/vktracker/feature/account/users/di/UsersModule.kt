package ru.vktracker.feature.account.users.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vktracker.core.common.text.UsernameFormat
import ru.vktracker.core.ui.paging.ProvidePagingConfig
import ru.vktracker.data.account.BaseAccountTrackedUsersRepository
import ru.vktracker.data.account.cache.tracking.AccountTrackedUsersCacheDataSource
import ru.vktracker.data.account.cache.tracking.ProvideTrackedUsersDao
import ru.vktracker.data.account.cloud.UsersCloudToUsersMapper
import ru.vktracker.feature.account.users.domain.AccountTrackedUsersRepository
import ru.vktracker.feature.account.users.ui.UserToUiMapper
import javax.inject.Singleton

/**
 * @author Danil Glazkov on 10.06.2023, 03:30
 */
@Module
@InstallIn(SingletonComponent::class)
class UsersModule {

    @Singleton
    @Provides
    fun ProvidePagingConfig(): ProvidePagingConfig =
        ProvidePagingConfig.VkApiFriends()

    @Singleton
    @Provides
    fun provideUsersCloudToUsersMapper(usernameFormat: UsernameFormat): UsersCloudToUsersMapper {
        return UsersCloudToUsersMapper.Base(usernameFormat)
    }

    @Singleton
    @Provides
    fun provideMapperToUi(repository: AccountTrackedUsersRepository): UserToUiMapper {
        return UserToUiMapper.Base(repository)
    }

    @Singleton
    @Provides
    fun provideAccountTrackedUsersRepository(
        dao: ProvideTrackedUsersDao
    ): AccountTrackedUsersRepository {
        return BaseAccountTrackedUsersRepository(
            AccountTrackedUsersCacheDataSource.Base(dao.provideTrackedUsersDao())
        )
    }

}