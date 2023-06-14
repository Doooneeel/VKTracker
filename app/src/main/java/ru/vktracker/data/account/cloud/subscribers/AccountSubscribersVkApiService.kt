package ru.vktracker.data.account.cloud.subscribers

import com.vk.api.sdk.requests.VKRequest
import com.vk.sdk.api.users.UsersService
import com.vk.sdk.api.users.dto.UsersFields
import com.vk.sdk.api.users.dto.UsersGetFollowersFieldsResponse
import ru.vktracker.core.common.Page
import com.vk.sdk.api.users.dto.UsersGetFollowersFieldsResponse as FollowersResponse
import ru.vktracker.data.core.VkApiService

/**
 * @author Danil Glazkov on 07.06.2023, 02:21
 */
interface AccountSubscribersVkApiService: VkApiService.Paging<FollowersResponse> {

    class Base : VkApiService.AbstractPaging<FollowersResponse>(), AccountSubscribersVkApiService {

        private val service =  UsersService()

        override fun command(page: Page): VKRequest<UsersGetFollowersFieldsResponse> {
            return service.usersGetFollowers(
                count = page.count(),
                offset = page.offset(),
                fields = listOf(UsersFields.PHOTO_100)
            )
        }
    }

}