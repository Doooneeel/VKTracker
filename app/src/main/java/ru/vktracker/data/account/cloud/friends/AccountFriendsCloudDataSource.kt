package ru.vktracker.data.account.cloud.friends

import com.vk.sdk.api.users.dto.UsersUserFull
import ru.vktracker.core.common.Page
import ru.vktracker.core.common.Users
import ru.vktracker.data.account.cloud.UsersCloudToUsersMapper

/**
 * @author Danil Glazkov on 10.06.2023, 19:03
 */
interface AccountFriendsCloudDataSource {

    suspend fun requestFriends(page: Page): Users


    class Base(
        private val friendsService: AccountFriendsVkApiService,
        private val mapper: UsersCloudToUsersMapper
    ) : AccountFriendsCloudDataSource {
        override suspend fun requestFriends(page: Page): Users {
            val friends: List<UsersUserFull> = friendsService.execute(page).items
            return mapper.map(friends)
        }
    }

}