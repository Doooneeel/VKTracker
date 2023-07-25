package ru.vktracker.feature.login.welcome.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.vktracker.R
import ru.vktracker.databinding.LayoutWelcomeItemBinding

/**
 * @author Danil Glazkov on 03.07.2023, 9:26
 */
interface WelcomeUiState {

    fun update(binding: LayoutWelcomeItemBinding)


    abstract class Abstract(
        @DrawableRes private val icon: Int,
        @StringRes private val title: Int,
        @StringRes private val description: Int,
    ) : WelcomeUiState {
        override fun update(binding: LayoutWelcomeItemBinding) = binding.run {
            iconImageView.setImageResource(icon)
            titleTextView.setText(title)
            titleTextView.isSelected = true
            descriptionTextView.setText(description)
        }
    }

    object Tracking : Abstract(
        R.drawable.ic_eye_outline,
        R.string.welcome_title_tracking,
        R.string.welcome_description_tracking
    )

    object Design : Abstract(
        R.drawable.ic_palette_outline,
        R.string.welcome_title_design,
        R.string.welcome_description_design
    )

}