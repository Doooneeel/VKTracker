package ru.vktracker.feature.users.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vktracker.core.common.text.UsernameFormat
import ru.vktracker.core.ui.paging.ProvidePagingConfig
import ru.vktracker.core.ui.resources.ManageResources
import ru.vktracker.data.account.BaseAccountTrackedUsersRepository
import ru.vktracker.data.account.cache.tracking.AccountTrackedUsersCacheDataSource
import ru.vktracker.data.account.cache.tracking.ProvideTrackedUsersDao
import ru.vktracker.data.account.cloud.UserCloudToUserMapper
import ru.vktracker.feature.users.domain.AccountTrackedUsersRepository
import ru.vktracker.feature.users.ui.HandleAccountUsersExceptions
import ru.vktracker.feature.users.ui.UserToUiMapper
import javax.inject.Singleton

/**
 * @author Danil Glazkov on 10.06.2023, 03:30
 */
@Module
@InstallIn(SingletonComponent::class)
class AccountUsersModule {

    @Singleton
    @Provides
    fun ProvidePagingConfig(): ProvidePagingConfig = ProvidePagingConfig.Base()

    @Singleton
    @Provides
    fun provideUsersCloudToUsersMapper(usernameFormat: UsernameFormat): UserCloudToUserMapper {
        return UserCloudToUserMapper.Base(usernameFormat)
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
    ) : AccountTrackedUsersRepository {
        return BaseAccountTrackedUsersRepository(
            AccountTrackedUsersCacheDataSource.Base(dao.provideTrackedUsersDao())
        )
    }

    @Singleton
    @Provides
    fun provideHandleException(resources: ManageResources): HandleAccountUsersExceptions =
        HandleAccountUsersExceptions.Base(resources)
}