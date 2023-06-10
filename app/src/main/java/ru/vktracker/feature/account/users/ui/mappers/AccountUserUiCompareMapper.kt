package ru.vktracker.feature.account.users.ui.mappers

import ru.vktracker.feature.account.users.ui.AccountUserUi

/**
 * @author Danil Glazkov on 02.06.2023, 12:44
 */
interface AccountUserUiCompareMapper : AccountUserUi.Mapper<Boolean> {

    class Id(private val id: Long) : AccountUserUiCompareMapper {
        override fun map(id: Long, name: String, avatar: String, tracked: Boolean): Boolean =
            this.id == id
    }

    class IdAndName(private val id: Long, private val name: String) : AccountUserUiCompareMapper {
        override fun map(id: Long, name: String, avatar: String, tracked: Boolean): Boolean =
            this.id == id && this.name == name
    }

    class Content(
        private val id: Long,
        private val name: String,
        private val avatar: String,
        private val tracked: Boolean
    ) : AccountUserUiCompareMapper {
        override fun map(id: Long, name: String, avatar: String, tracked: Boolean): Boolean =
            this.id == id && this.name == name && this.avatar == avatar && this.tracked == tracked
    }

    class Object(private val friend: AccountUserUi) : AccountUserUiCompareMapper {
        override fun map(id: Long, name: String, avatar: String, tracked: Boolean): Boolean =
            friend.map(Content(id, name, avatar, tracked))
    }

}