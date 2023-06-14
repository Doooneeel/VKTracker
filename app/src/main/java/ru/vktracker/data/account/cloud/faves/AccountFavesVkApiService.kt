package ru.vktracker.data.account.cloud.faves

import com.vk.api.sdk.requests.VKRequest
import com.vk.sdk.api.base.dto.BaseUserGroupFields
import com.vk.sdk.api.fave.FaveService
import com.vk.sdk.api.fave.dto.FaveGetPagesResponse
import com.vk.sdk.api.fave.dto.FaveGetPagesType
import ru.vktracker.core.common.Page
import ru.vktracker.data.core.VkApiService

/**
 * @author Danil Glazkov on 07.06.2023, 02:13
 */
interface AccountFavesVkApiService : VkApiService.Paging<FaveGetPagesResponse> {

    class Base : VkApiService.AbstractPaging<FaveGetPagesResponse>(), AccountFavesVkApiService {

        private val service = FaveService()

        override fun command(page: Page): VKRequest<FaveGetPagesResponse> {
            return service.faveGetPages(
                offset = page.offset(),
                count = page.count(),
                type = FaveGetPagesType.USERS,
                fields = listOf(BaseUserGroupFields.PHOTO_100)
            )
        }
    }

}