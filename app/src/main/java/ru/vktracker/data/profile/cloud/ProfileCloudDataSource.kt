package ru.vktracker.data.profile.cloud

import ru.vktracker.core.common.user.User
import ru.vktracker.core.common.text.UsernameFormat

/**
 * @author Danil Glazkov on 17.06.2023, 11:59
 */
interface ProfileCloudDataSource {

    suspend fun requestProfile(): User


    class Base (
        private val service: ProfileVkApiService,
        private val usernameFormat: UsernameFormat
    ) : ProfileCloudDataSource {
        override suspend fun requestProfile(): User {
            return service.execute().run {
                User.Base(id.value, usernameFormat.format(firstName, lastName), photo200 ?: "")
            }
        }
    }

}