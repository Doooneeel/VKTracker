package ru.vktracker.feature.account.users.ui

import ru.vktracker.core.ui.DiffUtilCallback
import ru.vktracker.core.ui.view.AbstractView.*
import ru.vktracker.feature.account.users.ui.mappers.AccountUserUiCompareMapper

/**
 * @author Danil Glazkov on 02.06.2023, 12:08
 */
interface AccountUserUi : DiffUtilCallback.Same<AccountUserUi> {

    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {
        fun map(id: Long, name: String, avatar: String, tracked: Boolean): T
    }

    fun apply(name: Text, image: ImageUrl, tracked: ToggleButton)

    fun applyHeader(text: Text)


    abstract class Abstract(
        private val id: Long,
        private val name: String,
        private val avatar: String,
        private val tracked: Boolean
    ) : AccountUserUi {

        override fun apply(name: Text, image: ImageUrl, tracked: ToggleButton) = Unit

        override fun applyHeader(text: Text) = Unit

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(id, name, avatar, tracked)

        override fun same(data: AccountUserUi): Boolean =
            data.map(AccountUserUiCompareMapper.Id(id))

        override fun sameContent(data: AccountUserUi): Boolean =
            data.map(AccountUserUiCompareMapper.Content(id, name, avatar, tracked))
    }

    data class Base(
        private val id: Long,
        private val name: String,
        private val avatar: String,
        private val tracked: Boolean
    ) : Abstract(id, name, avatar, tracked) {

        override fun apply(name: Text, image: ImageUrl, tracked: ToggleButton) {
            name.apply(this.name)
            tracked.apply(this.tracked)
            image.load(avatar)
        }
    }

    data class Header(private val text: String) : Abstract(Long.MIN_VALUE, text, "", false) {

        override fun same(data: AccountUserUi): Boolean =
            data.map(AccountUserUiCompareMapper.IdAndName(Long.MIN_VALUE, text))

        override fun applyHeader(text: Text) {
            text.apply(this.text)
        }
    }

    data class Message(private val text: String) : Abstract(Long.MIN_VALUE, text, "", false) {

        override fun same(data: AccountUserUi): Boolean =
            data.map(AccountUserUiCompareMapper.IdAndName(Long.MIN_VALUE, text))

        override fun applyHeader(text: Text) {
            text.apply(this.text)
        }
    }

}