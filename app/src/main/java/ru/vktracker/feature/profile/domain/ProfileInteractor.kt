package ru.vktracker.feature.profile.domain

import ru.vktracker.core.common.User

/**
 * @author Danil Glazkov on 17.06.2023, 11:45
 */
interface ProfileInteractor {

    suspend fun fetchProfile(): User

    class Base(private val repository: ProfileRepository) : ProfileInteractor {
        override suspend fun fetchProfile() = repository.loadProfile()
    }

}