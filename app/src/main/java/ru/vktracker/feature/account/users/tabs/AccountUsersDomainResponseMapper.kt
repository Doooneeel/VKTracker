package ru.vktracker.feature.account.users.tabs

import ru.vktracker.R
import ru.vktracker.core.common.Users
import ru.vktracker.core.ui.HandleUiError
import ru.vktracker.core.ui.resources.ProvideString
import ru.vktracker.feature.account.users.domain.AccountUsersDomainResponse
import ru.vktracker.feature.account.users.ui.AccountUsersCommunication
import ru.vktracker.feature.account.users.ui.AccountUsersUi
import ru.vktracker.feature.account.users.ui.mappers.UsersToUiMapper
import java.lang.Exception

/**
 * @author Danil Glazkov on 10.06.2023, 08:07
 */
interface AccountUsersDomainResponseMapper : AccountUsersDomainResponse.Mapper<Unit> {

    abstract class Abstract(
        private val communication: AccountUsersCommunication,
        private val mapperToUi: UsersToUiMapper,
        private val handleUiError: HandleUiError,
    ) : AccountUsersDomainResponseMapper {

        protected abstract val emptyUserListMessage: String

        override fun failure(exception: Exception) =
            communication.put(AccountUsersUi.Message(handleUiError.handle(exception)))

        override fun success(users: Users) {
            if (users.asList().isEmpty()) {
                communication.put(AccountUsersUi.Message(emptyUserListMessage))
            } else {
                val usersUi: AccountUsersUi = users.map(mapperToUi)
                communication.put(usersUi)
            }
        }
    }

    class Faves(
        communication: AccountUsersCommunication,
        mapperToUi: UsersToUiMapper,
        handleUiError: HandleUiError,
        resources: ProvideString.Single
    ) : Abstract(communication, mapperToUi, handleUiError) {
        override val emptyUserListMessage = resources.string(R.string.no_users_in_faves)
    }

    class Friends(
        communication: AccountUsersCommunication,
        mapperToUi: UsersToUiMapper,
        handleUiError: HandleUiError,
        resources: ProvideString.Single
    ) : Abstract(communication, mapperToUi, handleUiError) {
        override val emptyUserListMessage = resources.string(R.string.no_friends)
    }

    class Subscribers(
        communication: AccountUsersCommunication,
        mapperToUi: UsersToUiMapper,
        handleUiError: HandleUiError,
        resources: ProvideString.Single
    ) : Abstract(communication, mapperToUi, handleUiError) {
        override val emptyUserListMessage = resources.string(R.string.no_subscribers)
    }

}