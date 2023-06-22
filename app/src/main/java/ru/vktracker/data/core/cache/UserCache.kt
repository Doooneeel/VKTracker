package ru.vktracker.data.core.cache

/**
 * @author Danil Glazkov on 20.06.2023, 18:50
 */
data class UserCache(
    val id: Long,
    val name: String,
    val avatar: String
) {
    fun <T> map(mapper: Mapper<T>): T = mapper.map(id, name, avatar)

    interface Mapper<T> {
        fun map(id: Long, name: String, avatar: String): T
    }

}