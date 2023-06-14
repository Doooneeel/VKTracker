package ru.vktracker.feature.account.users.ui

import ru.vktracker.R
import ru.vktracker.core.ui.diffutil.Same
import ru.vktracker.core.ui.view.AbstractView.*

/**
 * @author Danil Glazkov on 02.06.2023, 12:08
 */
interface AccountUserUi : Same<AccountUserUi> {

    fun <T> map(mapper: Mapper<T>): T

    fun apply(name: Text, image: ImageUrl, tracked: ToggleButton)

    interface Mapper<T> {
        fun map(id: Long, name: String, avatar: String, tracked: Boolean): T
    }

    fun changeTracked(state: Boolean)

    data class Base(
        private val id: Long,
        private val name: String,
        private val avatar: String,
        private var tracked: Boolean
    ) : AccountUserUi {

        override fun changeTracked(state: Boolean) { tracked = state }

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(id, name, avatar, tracked)

        override fun same(data: AccountUserUi): Boolean =
            data.map(AccountUserUiCompareMapper.Id(id))

        override fun sameContent(data: AccountUserUi): Boolean =
            data.map(AccountUserUiCompareMapper.Content(id, name, avatar, tracked))


        override fun apply(name: Text, image: ImageUrl, tracked: ToggleButton) {
            name.apply(this.name)
            tracked.apply(this.tracked)
            image.load(avatar, R.color.transparent)
        }
    }

}