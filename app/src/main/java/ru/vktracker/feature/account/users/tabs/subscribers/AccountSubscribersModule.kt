package ru.vktracker.feature.account.users.tabs.subscribers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vktracker.core.common.CurrentTime
import ru.vktracker.core.ui.paging.ProvidePagingConfig
import ru.vktracker.data.account.BaseAccountUsersRepository
import ru.vktracker.data.account.cloud.UsersCloudToUsersMapper
import ru.vktracker.data.account.cloud.subscribers.AccountSubscribersCloudDataSource
import ru.vktracker.data.account.cloud.subscribers.AccountSubscribersVkApiService
import ru.vktracker.feature.account.users.domain.AccountTrackedUsersRepository
import ru.vktracker.feature.account.users.ui.AccountUsersCommunication
import ru.vktracker.feature.account.users.domain.AccountUsersInteractor
import ru.vktracker.feature.account.users.ui.ProvideAccountUsersPagingSource
import ru.vktracker.feature.account.users.ui.UserToUiMapper
import javax.inject.Qualifier

/**
 * @author Danil Glazkov on 10.06.2023, 03:06
 */
@Module
@AccountSubscribersModule.ModuleQualifier
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
        mapper: UserToUiMapper
    ) : AccountUsersCommunication = AccountUsersCommunication.Base(
        config,
        ProvideAccountUsersPagingSource(
            interactor,
            mapper
        )
    )

    @Provides
    @ModuleQualifier
    fun provideInteractor(
        trackedUsersRepository: AccountTrackedUsersRepository,
        mapper: UsersCloudToUsersMapper,
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