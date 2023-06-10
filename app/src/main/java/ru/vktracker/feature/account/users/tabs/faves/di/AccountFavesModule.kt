package ru.vktracker.feature.account.users.tabs.faves.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.ui.HandleUiError
import ru.vktracker.core.ui.resources.ManageResources
import ru.vktracker.feature.account.users.ui.AccountUsersCommunication
import ru.vktracker.feature.account.users.domain.AccountUsersInteractor
import ru.vktracker.feature.account.users.domain.AccountUsersRepository
import ru.vktracker.feature.account.users.tabs.AccountUsersDomainResponseMapper
import ru.vktracker.feature.account.users.tabs.AccountUsersHandleDomainRequest
import ru.vktracker.feature.account.users.ui.mappers.UserToUiMapper
import ru.vktracker.feature.account.users.ui.mappers.UsersToUiMapper
import javax.inject.Named

/**
 * @author Danil Glazkov on 10.06.2023, 02:22
 */
@Module
@InstallIn(ViewModelComponent::class)
class AccountFavesModule {

    private val communication =  AccountUsersCommunication.Base()

    @Provides
    @Named(INTERACTOR_NAME)
    fun provideAccountFavesInteractor(repository: AccountUsersRepository) : AccountUsersInteractor =
        AccountUsersInteractor.Base(repository)

    @Provides
    @Named(COMMUNICATION_NAME)
    fun provideFavesCommunication(): AccountUsersCommunication = communication


    @Provides
    @Named(HANDLE_REQUEST)
    fun provideFavesHandleDomainRequest(
        dispatchers: CoroutineDispatchers,
        userToUiMapper: UserToUiMapper,
        handleUiError: HandleUiError,
        resources: ManageResources
    ) : AccountUsersHandleDomainRequest {
        return AccountUsersHandleDomainRequest.Base(
            AccountUsersDomainResponseMapper.Faves(
                communication,
                UsersToUiMapper.Base(userToUiMapper),
                handleUiError,
                resources
            ),
            dispatchers
        )
    }

    companion object {
        const val INTERACTOR_NAME = "AccountFavesInteractor"
        const val COMMUNICATION_NAME = "AccountFavesCommunication"
        const val HANDLE_REQUEST = "AccountFavesHandleRequest"
    }

}