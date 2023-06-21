package ru.vktracker.feature.users.ui

/**
 * @author Danil Glazkov on 02.06.2023, 12:44
 */
interface AccountUserUiCompareMapper : AccountUserUi.Mapper<Boolean> {

    class Id(private val id: Long) : AccountUserUiCompareMapper {
        override fun map(id: Long, name: String, avatar: String, tracked: Boolean) =
            this.id == id
    }

    class Content(
        private val id: Long,
        private val name: String,
        private val avatar: String,
        private val tracked: Boolean
    ) : AccountUserUiCompareMapper {
        override fun map(id: Long, name: String, avatar: String, tracked: Boolean) =
            this.id == id && this.tracked == tracked &&
                    this.name == name && this.avatar == avatar
    }

}