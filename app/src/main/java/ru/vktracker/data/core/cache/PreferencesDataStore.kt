package ru.vktracker.data.core.cache

import android.content.SharedPreferences
import kotlin.jvm.Throws

/**
 * @author Danil Glazkov on 09.06.2023, 23:01
 */
interface PreferencesDataStore {

    interface Read<T> {

        @Throws(IllegalStateException::class)
        fun read(key: String): T

        fun readWithDefault(key: String, default: T): T

    }

    interface Save<T> {
        fun save(key: String, data: T)
    }

    interface Mutable<T> : Read<T>, Save<T>


    class Str(private val preferences: SharedPreferences) : Mutable<String> {

        override fun read(key: String): String =
            preferences.getString(key, null) ?: throw IllegalStateException("Empty storage: $key")

        override fun readWithDefault(key: String, default: String): String =
            preferences.getString(key, default) ?: default

        override fun save(key: String, data: String) = preferences.edit()
            .putString(key, data)
            .apply()
    }


    abstract class Json<T>(
        preferences: SharedPreferences,
        private val serialization: Serialization,
        private val clazz: Class<T>
    ) : Mutable<T> {

        private val stringDataStore = Str(preferences)

        override fun readWithDefault(key: String, default: T): T {
            return try {
                val json = stringDataStore.readWithDefault(key, "")

                if (json.isEmpty()) {
                    return default
                }
                serialization.fromJson(json, clazz)
            } catch (exception: Exception) {
                default
            }
        }

        override fun read(key: String): T =
            serialization.fromJson(stringDataStore.read(key), clazz)

        override fun save(key: String, data: T) =
            stringDataStore.save(key, serialization.toJson(data))

    }

}