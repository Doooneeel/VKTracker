package ru.vktracker.feature.profile.domain

import ru.vktracker.core.common.user.User

/**
 * @author Danil Glazkov on 17.06.2023, 11:45
 */
interface ProfileInteractor {

    suspend fun fetchProfile(profile: (User) -> Unit)


    class Base (private val repository: ProfileRepository) : ProfileInteractor {

        private val errorProfile = User.Empty

        override suspend fun fetchProfile(profile: (User) -> Unit) {
            val cachedProfile = runCatching { repository.cachedProfile() }.getOrDefault(errorProfile)
            profile.invoke(cachedProfile)

            val cloudProfile = runCatching { repository.cloudProfile() }.getOrDefault(errorProfile)

            if (cachedProfile.compare(cloudProfile).not()) {
                profile.invoke(cloudProfile)
            }
        }

    }

}