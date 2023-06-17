package ru.vktracker.feature.profile.domain

import ru.vktracker.core.common.User

/**
 * @author Danil Glazkov on 17.06.2023, 11:46
 */
interface ProfileRepository {
    suspend fun loadProfile(): User
}