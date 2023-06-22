package ru.vktracker.data.account.cloud.friends

import ru.vktracker.core.common.Page
import ru.vktracker.core.common.user.User
import ru.vktracker.data.account.cloud.UserCloudToUserMapper

/**
 * @author Danil Glazkov on 10.06.2023, 19:03
 */
interface AccountFriendsCloudDataSource {

    suspend fun requestFriends(page: Page): List<User>


    class Base(
        private val friendsService: AccountFriendsVkApiService,
        private val mapper: UserCloudToUserMapper
    ) : AccountFriendsCloudDataSource {
        override suspend fun requestFriends(page: Page): List<User> {
            return friendsService.execute(page).items.map { friendsCloud ->
                mapper.map(friendsCloud)
            }
        }
    }

}