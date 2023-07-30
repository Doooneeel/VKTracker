package ru.vktracker.feature.profile.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.vktracker.core.ui.view.common.AbstractView.*

/**
 * @author Danil Glazkov on 17.06.2023, 07:15
 */
interface ProfileUi : Parcelable {

    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {
        fun map(id: String, name: String, avatar: String): T
    }

    fun apply(id: Text, name: Text, avatar: ImageUrl)


    @Parcelize
    class Base (
        private val id: String,
        private val name: String,
        private val avatar: String,
    ) : ProfileUi {

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(id, name, avatar)

        override fun apply(id: Text, name: Text, avatar: ImageUrl) {
            id.apply(this.id)
            name.apply(this.name)
            avatar.load(this.avatar)
        }
    }

    @Parcelize
    object Error : ProfileUi {

        override fun apply(id: Text, name: Text, avatar: ImageUrl) = avatar.load("")

        override fun <T> map(mapper: Mapper<T>): T = mapper.map("id", "", "")

    }

}