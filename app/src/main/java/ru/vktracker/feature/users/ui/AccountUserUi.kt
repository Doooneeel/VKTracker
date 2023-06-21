package ru.vktracker.feature.users.ui

import ru.vktracker.core.ui.diffutil.Same
import ru.vktracker.core.ui.view.AbstractView.*

/**
 * @author Danil Glazkov on 02.06.2023, 12:08
 */
interface AccountUserUi : Same<AccountUserUi> {

    fun <T> map(mapper: Mapper<T>): T

    fun apply(name: Text, image: ImageUrl, tracked: ToggleButton)

    fun applyMessage(message: Text)

    fun changeTrackingStatus(tracked: Boolean)

    interface Mapper<T> {
        fun map(id: Long, name: String, avatar: String, tracked: Boolean): T
    }

    abstract class Abstract(
        private val id: Long,
        private val name: String,
        private val avatar: String,
        private var tracked: Boolean
    ) : AccountUserUi {

        override fun changeTrackingStatus(tracked: Boolean) { this.tracked = tracked }

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(id, name, avatar, tracked)

        override fun same(data: AccountUserUi): Boolean =
            data.map(AccountUserUiCompareMapper.Id(id))

        override fun sameContent(data: AccountUserUi): Boolean =
            data.map(AccountUserUiCompareMapper.Content(id, name, avatar, tracked))

        override fun apply(name: Text, image: ImageUrl, tracked: ToggleButton) = Unit

        override fun applyMessage(message: Text) = Unit
    }


    data class Base(
        private val id: Long,
        private val name: String,
        private val avatar: String,
        private val tracked: Boolean
    ) : Abstract(id, name, avatar, tracked) {
        override fun apply(name: Text, image: ImageUrl, tracked: ToggleButton) {
            image.load(avatar)
            name.apply(this.name)
            tracked.apply(this.tracked)
        }
    }

    data class Message(private val message: String) : Abstract(-1, message, "", false) {

        override fun applyMessage(message: Text) = message.apply(this.message)

        override fun changeTrackingStatus(tracked: Boolean) = Unit

    }

}