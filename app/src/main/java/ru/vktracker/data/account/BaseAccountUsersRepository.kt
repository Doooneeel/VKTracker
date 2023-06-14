package ru.vktracker.data.account

import ru.vktracker.core.common.Page
import ru.vktracker.core.common.Users
import ru.vktracker.data.account.cloud.faves.AccountFavesCloudDataSource
import ru.vktracker.data.account.cloud.friends.AccountFriendsCloudDataSource
import ru.vktracker.data.account.cloud.subscribers.AccountSubscribersCloudDataSource
import ru.vktracker.feature.account.users.domain.AccountUsersRepository

/**
 * @author Danil Glazkov on 10.06.2023, 09:12
 */
interface BaseAccountUsersRepository : AccountUsersRepository {

    class Friends(
        private val cloudDataSource: AccountFriendsCloudDataSource
    ) : BaseAccountUsersRepository {
        override suspend fun accountUsers(page: Page): Users =
            cloudDataSource.requestFriends(page)
    }

    class Faves(
        private val cloudDataSource: AccountFavesCloudDataSource
    ) : BaseAccountUsersRepository {
        override suspend fun accountUsers(page: Page): Users =
            cloudDataSource.requestFaves(page)
    }

    class Subscribers(
        private val cloudDataSource: AccountSubscribersCloudDataSource
    ) : BaseAccountUsersRepository {
        override suspend fun accountUsers(page: Page): Users =
            cloudDataSource.requestSubscribers(page)
    }
}