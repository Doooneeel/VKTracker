package ru.vktracker.data.profile.cache

import android.content.SharedPreferences
import ru.vktracker.data.core.cache.PreferencesDataStore
import ru.vktracker.data.core.cache.Serialization
import ru.vktracker.data.core.cache.UserCache

/**
 * @author Danil Glazkov on 17.06.2023, 12:11
 */
class ProfilePreferencesDataStore(
    sharedPreferences: SharedPreferences,
    serialization: Serialization
) : PreferencesDataStore.JSON<UserCache>(
    sharedPreferences,
    serialization,
    UserCache::class.java
)