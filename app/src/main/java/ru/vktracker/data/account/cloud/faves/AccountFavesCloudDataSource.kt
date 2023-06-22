package ru.vktracker.data.account.cloud.faves

import ru.vktracker.core.common.Page
import ru.vktracker.core.common.user.User
import ru.vktracker.data.account.cloud.UserCloudToUserMapper

/**
 * @author Danil Glazkov on 10.06.2023, 19:06
 */
interface AccountFavesCloudDataSource {

    suspend fun requestFaves(page: Page): List<User>


    class Base(
        private val service: AccountFavesVkApiService,
        private val mapper: UserCloudToUserMapper
    ) : AccountFavesCloudDataSource {

        override suspend fun requestFaves(page: Page): List<User> {
            return service.execute(page).items!!.map { it.user!! }.map { userCloud ->
                mapper.map(userCloud)
            }
        }
    }
}