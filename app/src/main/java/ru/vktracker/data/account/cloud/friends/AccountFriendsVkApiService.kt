package ru.vktracker.data.account.cloud.friends

import com.vk.api.sdk.requests.VKRequest
import com.vk.sdk.api.friends.FriendsService
import com.vk.sdk.api.friends.dto.FriendsGetFieldsResponse
import com.vk.sdk.api.friends.dto.FriendsGetOrder
import com.vk.sdk.api.users.dto.UsersFields.*
import ru.vktracker.core.common.Page
import ru.vktracker.data.core.VkApiService

/**
 * @author Danil Glazkov on 04.06.2023, 20:26
 */
interface AccountFriendsVkApiService : VkApiService.Paging<FriendsGetFieldsResponse> {

    class Base : VkApiService.AbstractPaging<FriendsGetFieldsResponse>(), AccountFriendsVkApiService {

        private val service = FriendsService()

        override fun command(page: Page): VKRequest<FriendsGetFieldsResponse> {
            return service.friendsGet(
                offset = page.offset(),
                count = page.count(),
                fields = listOf(PHOTO_100, FIRST_NAME_NOM, LAST_NAME_NOM),
                order = FriendsGetOrder.HINTS
            )
        }
    }
}