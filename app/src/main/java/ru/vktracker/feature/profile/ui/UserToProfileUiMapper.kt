package ru.vktracker.feature.profile.ui

import ru.vktracker.core.common.User

/**
 * @author Danil Glazkov on 17.06.2023, 11:51
 */
interface UserToProfileUiMapper : User.Mapper<ProfileUi> {

    class Base : UserToProfileUiMapper {
        override fun map(id: Long, name: String, avatar: String): ProfileUi =
            ProfileUi.Base("@id$id", name, avatar)
    }
}