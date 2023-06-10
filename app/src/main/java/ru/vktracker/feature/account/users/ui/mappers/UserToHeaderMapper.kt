package ru.vktracker.feature.account.users.ui.mappers

import ru.vktracker.core.common.User
import java.util.Locale

/**
 * @author Danil Glazkov on 05.06.2023, 04:15
 */
interface UserToHeaderMapper : User.Mapper<String> {

    class FirstCharacterOfName(private val locale: Locale) : UserToHeaderMapper {
        override fun map(id: Long, name: String, avatar: String): String =
            if (name.isEmpty()) "*" else name[0].uppercase(locale)
    }
}