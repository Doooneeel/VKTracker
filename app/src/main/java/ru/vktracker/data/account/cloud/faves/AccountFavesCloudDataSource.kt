package ru.vktracker.data.account.cloud.faves

import ru.vktracker.core.common.Page
import ru.vktracker.core.common.Users
import ru.vktracker.data.account.cloud.UsersCloudToUsersMapper

/**
 * @author Danil Glazkov on 10.06.2023, 19:06
 */
interface AccountFavesCloudDataSource {

    suspend fun requestFaves(page: Page): Users


    class Base(
        private val service: AccountFavesVkApiService,
        private val mapper: UsersCloudToUsersMapper
    ) : AccountFavesCloudDataSource {

        override suspend fun requestFaves(page: Page): Users {
            val favePages = service.execute(page).items!!.map { it.user!! }
            return mapper.map(favePages)
        }
    }
}