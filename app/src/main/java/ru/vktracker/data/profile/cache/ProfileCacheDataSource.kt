package ru.vktracker.data.profile.cache

/**
 * @author Danil Glazkov on 17.06.2023, 12:15
 */
interface ProfileCacheDataSource {

    fun read(): ProfileCache

    fun save(profile: ProfileCache)


    class Base(private val dataStore: ProfilePreferencesDataStore) : ProfileCacheDataSource {

        override fun read(): ProfileCache =
            dataStore.read(PROFILE_KEY, ProfileCache(0, "", ""))

        override fun save(profile: ProfileCache) = dataStore.save(PROFILE_KEY, profile)

        companion object {
            private const val PROFILE_KEY = "profileKey"
        }
    }

}