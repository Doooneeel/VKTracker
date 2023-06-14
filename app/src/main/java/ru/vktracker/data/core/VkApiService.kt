package ru.vktracker.data.core

import com.vk.api.sdk.VK
import com.vk.api.sdk.exceptions.VKApiException
import com.vk.api.sdk.requests.VKRequest
import kotlinx.coroutines.delay
import ru.vktracker.core.common.Page
import java.io.IOException

/**
 * @author Danil Glazkov on 01.06.2023, 16:13
 */
interface VkApiService {

    interface Paging<T> {
        @Throws(InterruptedException::class, IOException::class, VKApiException::class)
        suspend fun execute(page: Page): T
    }

    interface NoPaging<T> {
        @Throws(InterruptedException::class, IOException::class, VKApiException::class)
        suspend fun execute(): T
    }

    abstract class AbstractPaging<T> : Paging<T> {

        protected abstract fun command(page: Page): VKRequest<T>

        override suspend fun execute(page: Page): T {
            return VK.executeSync(command(page))
        }
    }
}