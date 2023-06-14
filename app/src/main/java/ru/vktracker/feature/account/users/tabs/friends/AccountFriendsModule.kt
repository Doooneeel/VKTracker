package ru.vktracker.feature.account.users.tabs.friends

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vktracker.core.common.CurrentTime
import ru.vktracker.core.ui.paging.ProvidePagingConfig
import ru.vktracker.data.account.BaseAccountUsersRepository
import ru.vktracker.data.account.cloud.UsersCloudToUsersMapper
import ru.vktracker.data.account.cloud.friends.AccountFriendsCloudDataSource
import ru.vktracker.data.account.cloud.friends.AccountFriendsVkApiService
import ru.vktracker.feature.account.users.domain.AccountTrackedUsersRepository
import ru.vktracker.feature.account.users.ui.AccountUsersCommunication
import ru.vktracker.feature.account.users.domain.AccountUsersInteractor
import ru.vktracker.feature.account.users.ui.ProvideAccountUsersPagingSource
import ru.vktracker.feature.account.users.ui.UserToUiMapper
import javax.inject.Qualifier

/**
 * @author Danil Glazkov on 10.06.2023, 03:04
 */
@Module
@AccountFriendsModule.ModuleQualifier
@InstallIn(ViewModelComponent::class)
class AccountFriendsModule {

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
        BaseAccountUsersRepository.Friends(
            AccountFriendsCloudDataSource.Base(
                AccountFriendsVkApiService.Base(),
                mapper
            )
        ),
        trackedUsersRepository,
        currentTime
    )
}