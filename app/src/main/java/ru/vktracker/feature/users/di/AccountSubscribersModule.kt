package ru.vktracker.feature.users.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vktracker.core.common.CurrentTime
import ru.vktracker.core.ui.paging.ProvidePagingConfig
import ru.vktracker.data.account.BaseAccountUsersRepository
import ru.vktracker.data.account.cloud.UserCloudToUserMapper
import ru.vktracker.data.account.cloud.subscribers.AccountSubscribersCloudDataSource
import ru.vktracker.data.account.cloud.subscribers.AccountSubscribersVkApiService
import ru.vktracker.feature.users.domain.AccountTrackedUsersRepository
import ru.vktracker.feature.users.domain.AccountUsersInteractor
import ru.vktracker.feature.users.ui.AccountUsersCommunication
import ru.vktracker.feature.users.ui.AccountUsersPagingSource
import ru.vktracker.feature.users.ui.HandleAccountUsersExceptions
import ru.vktracker.feature.users.ui.UserToUiMapper
import javax.inject.Qualifier

/**
 * @author Danil Glazkov on 10.06.2023, 03:06
 */
@Module
@InstallIn(ViewModelComponent::class)
class AccountSubscribersModule {

    @Qualifier
    annotation class ModuleQualifier

    @Provides
    @ModuleQualifier
    fun provideCommunication(
        config: ProvidePagingConfig,
        @ModuleQualifier
        interactor: AccountUsersInteractor,
        userToUiMapper: UserToUiMapper,
        handleExceptions: HandleAccountUsersExceptions
    ) : AccountUsersCommunication {
        return AccountUsersCommunication.Base(config) {
            AccountUsersPagingSource(interactor, userToUiMapper, handleExceptions)
        }
    }


    @Provides
    @ModuleQualifier
    fun provideInteractor(
        trackedUsersRepository: AccountTrackedUsersRepository,
        mapper: UserCloudToUserMapper,
        currentTime: CurrentTime
    ) : AccountUsersInteractor = AccountUsersInteractor.Base(
        BaseAccountUsersRepository.Subscribers(
            AccountSubscribersCloudDataSource.Base(
                AccountSubscribersVkApiService.Base(),
                mapper
            )
        ),
        trackedUsersRepository,
        currentTime
    )
}