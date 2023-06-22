package ru.vktracker.feature.profile.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import ru.vktracker.R
import ru.vktracker.databinding.LayoutProfileMenuItemBinding

/**
 * @author Danil Glazkov on 17.06.2023, 08:26
 */
class ProfileMenuItemLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
) : LinearLayoutCompat(
    context,
    attributeSet,
) {
    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.layout_profile_menu_item, this, true)
        val binding = LayoutProfileMenuItemBinding.bind(this)

        val typedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.ProfileMenuItemLayout
        )

        binding.titleTextView.text = typedArray.getString(
            R.styleable.ProfileMenuItemLayout_titleText
        )
        binding.descriptionTextView.text = typedArray.getString(
            R.styleable.ProfileMenuItemLayout_descriptionText
        )
        binding.imageView.setImageDrawable(typedArray.getDrawable(
            R.styleable.ProfileMenuItemLayout_icon)
        )
        typedArray.recycle()
    }

}