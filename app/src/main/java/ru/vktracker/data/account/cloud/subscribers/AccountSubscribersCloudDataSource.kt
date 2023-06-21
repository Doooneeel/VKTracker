package ru.vktracker.data.account.cloud.subscribers

import ru.vktracker.core.common.Page
import ru.vktracker.core.common.User
import ru.vktracker.data.account.cloud.UserCloudToUserMapper

/**
 * @author Danil Glazkov on 04.06.2023, 23:31
 */
interface AccountSubscribersCloudDataSource {

    suspend fun requestSubscribers(page: Page): List<User>


    class Base(
        private val service: AccountSubscribersVkApiService,
        private val mapper: UserCloudToUserMapper
    ) : AccountSubscribersCloudDataSource {
        override suspend fun requestSubscribers(page: Page): List<User> {
            return service.execute(page).items.map { subscribersCloud ->
                mapper.map(subscribersCloud )
            }
        }
    }
}