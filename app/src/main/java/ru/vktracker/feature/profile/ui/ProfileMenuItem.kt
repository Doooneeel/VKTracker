package ru.vktracker.feature.profile.ui

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import ru.vktracker.R
import ru.vktracker.databinding.LayoutProfileMenuItemBinding

/**
 * @author Danil Glazkov on 25.07.2023, 23:33
 */
interface ProfileMenuItem : Parcelable {

    fun apply(binding: LayoutProfileMenuItemBinding)


    abstract class Abstract(
        @StringRes private val title: Int,
        @StringRes private val description: Int,
        @DrawableRes private val icon: Int
    ) : ProfileMenuItem {
        override fun apply(binding: LayoutProfileMenuItemBinding) = binding.run {
            titleTextView.setText(title)
            iconImageView.setImageResource(icon)
            descriptionTextView.setText(description)
        }
    }

    @Parcelize
    class Settings : Abstract(
        R.string.settings,
        R.string.settings_description,
        R.drawable.ic_settings_outline
    )

    @Parcelize
    class Statistics : Abstract(
        R.string.statistics,
        R.string.statistics_description,
        R.drawable.ic_leaderboard_outline
    )

    @Parcelize
    class About : Abstract(
        R.string.about_app,
        R.string.app_version,
        R.drawable.ic_info_outline
    )

}