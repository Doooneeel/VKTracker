package ru.vktracker.data.core.cache

import com.google.gson.Gson

/**
 * @author Danil Glazkov on 09.06.2023, 23:02
 */
interface Serialization {

    fun <T> toJson(data: T): String

    fun <T> fromJson(json: String, clazz: Class<T>): T


    class GSON(private val gson: Gson) : Serialization {

        override fun <T> fromJson(json: String, clazz: Class<T>): T = gson.fromJson(json, clazz)

        override fun <T> toJson(data: T): String = gson.toJson(data)

    }

}