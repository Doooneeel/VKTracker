package ru.vktracker.data.core.cache

import android.content.SharedPreferences
import android.content.SharedPreferences.*
import androidx.core.content.edit

/**
 * @author Danil Glazkov on 09.06.2023, 23:01
 */
interface PreferencesDataStore {

    interface Read<T> {
        fun read(key: String, default: T): T
    }

    interface Save<T> {
        fun save(key: String, data: T)
    }

    interface Mutable<T> : Read<T>, Save<T>


    abstract class Abstract<T>(private val preferences: SharedPreferences) : Mutable<T> {

        protected abstract fun Editor.put(key: String, data: T)

        protected abstract fun SharedPreferences.get(key: String, default: T): T

        override fun save(key: String, data: T) =
            preferences.edit(true) { put(key, data) }

        override fun read(key: String, default: T): T =
            runCatching { preferences.get(key, default) }.getOrDefault(default)
    }


    class BOOLEAN(preferences: SharedPreferences) : Abstract<Boolean>(preferences) {
        override fun Editor.put(key: String, data: Boolean) { putBoolean(key, data) }
        override fun SharedPreferences.get(key: String, default: Boolean) = getBoolean(key, default)
    }

    class INT(preferences: SharedPreferences) : Abstract<Int>(preferences) {
        override fun Editor.put(key: String, data: Int) { putInt(key, data) }
        override fun SharedPreferences.get(key: String, default: Int): Int = getInt(key, default)
    }

    class STRING(preferences: SharedPreferences) : Abstract<String>(preferences) {
        override fun Editor.put(key: String, data: String) { putString(key, data) }
        override fun SharedPreferences.get(key: String, default: String) =
            getString(key, default) ?: default
    }

    abstract class JSON<T>(
        preferences: SharedPreferences,
        private val serialization: Serialization,
    ) : Abstract<T>(preferences) {

        protected abstract val clazz: Class<T>

        override fun Editor.put(key: String, data: T) { putString(key, serialization.toJson(data)) }

        override fun SharedPreferences.get(key: String, default: T): T = runCatching {
            val json = getString(key, "") ?: return default
            serialization.fromJson(json, clazz)
        }.getOrDefault(default)
    }

}