package ru.vktracker.data.core

import com.vk.api.sdk.VK
import com.vk.api.sdk.exceptions.VKApiException
import com.vk.api.sdk.requests.VKRequest
import java.io.IOException

/**
 * @author Danil Glazkov on 01.06.2023, 16:13
 */
interface VkApiService<T> {

    @Throws(
        InterruptedException::class,
        IOException::class,
        VKApiException::class
    )
    suspend fun execute(): T

    interface List<T> : VkApiService<kotlin.collections.List<T>>


    abstract class Abstract<T> : VkApiService<T> {

        protected abstract val command: VKRequest<T>

        override suspend fun execute(): T = VK.executeSync(command)

    }
}