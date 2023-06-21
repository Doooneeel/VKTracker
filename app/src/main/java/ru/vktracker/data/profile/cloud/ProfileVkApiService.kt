package ru.vktracker.data.profile.cloud

import com.vk.api.sdk.requests.VKRequest
import com.vk.sdk.api.account.AccountService
import com.vk.sdk.api.account.dto.AccountUserSettings
import ru.vktracker.data.core.VkApiService

/**
 * @author Danil Glazkov on 17.06.2023, 12:00
 */
interface ProfileVkApiService : VkApiService.NoPaging<AccountUserSettings> {

    class Base : VkApiService.AbstractNoPaging<AccountUserSettings>(), ProfileVkApiService {

        private val service = AccountService()

        override fun command(): VKRequest<AccountUserSettings> = service.accountGetProfileInfo()

    }
}