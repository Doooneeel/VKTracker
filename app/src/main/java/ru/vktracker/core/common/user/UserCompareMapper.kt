package ru.vktracker.core.common.user

/**
 * @author Danil Glazkov on 20.06.2023, 21:39
 */
interface UserCompareMapper : User.Mapper<Boolean> {

    class Object(
        private val id: Long,
        private val name: String,
        private val avatar: String
    ) : UserCompareMapper {
        override fun map(id: Long, name: String, avatar: String): Boolean =
            this.id == id && this.name == name && this.avatar == avatar
    }

    class Id(private val id: Long) : UserCompareMapper {
        override fun map(id: Long, name: String, avatar: String): Boolean = this.id == id
    }

}