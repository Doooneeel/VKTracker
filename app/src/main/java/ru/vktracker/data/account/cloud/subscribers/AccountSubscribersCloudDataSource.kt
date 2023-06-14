package ru.vktracker.data.account.cloud.subscribers

import ru.vktracker.core.common.Page
import ru.vktracker.core.common.Users
import ru.vktracker.data.account.cloud.UsersCloudToUsersMapper

/**
 * @author Danil Glazkov on 04.06.2023, 23:31
 */
interface AccountSubscribersCloudDataSource {

    suspend fun requestSubscribers(page: Page): Users


    class Base(
        private val service: AccountSubscribersVkApiService,
        private val mapper: UsersCloudToUsersMapper
    ) : AccountSubscribersCloudDataSource {
        override suspend fun requestSubscribers(page: Page): Users {
            val subscribers = service.execute(page).items
            return mapper.map(subscribers)
        }
    }
}