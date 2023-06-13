package ru.vktracker.feature.account.users.tabs.faves

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vktracker.core.common.CurrentTime
import ru.vktracker.core.ui.paging.ProvidePagingConfig
import ru.vktracker.data.account.BaseAccountUsersRepository
import ru.vktracker.data.account.cloud.UsersCloudToUsersMapper
import ru.vktracker.data.account.cloud.faves.AccountFavesCloudDataSource
import ru.vktracker.data.account.cloud.faves.AccountFavesVkApiService
import ru.vktracker.feature.account.users.domain.AccountTrackedUsersRepository
import ru.vktracker.feature.account.users.ui.AccountUsersCommunication
import ru.vktracker.feature.account.users.domain.AccountUsersInteractor
import ru.vktracker.feature.account.users.ui.ProvideAccountUsersPagingSource
import ru.vktracker.feature.account.users.ui.UserToUiMapper
import javax.inject.Qualifier

/**
 * @author Danil Glazkov on 10.06.2023, 02:22
 */
@Module
@AccountFavesModule.ModuleQualifier
@InstallIn(ViewModelComponent::class)
class AccountFavesModule {

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
        BaseAccountUsersRepository.Faves(
            AccountFavesCloudDataSource.Base(
                AccountFavesVkApiService.Base(),
                mapper
            )
        ),
        trackedUsersRepository,
        currentTime
    )
}