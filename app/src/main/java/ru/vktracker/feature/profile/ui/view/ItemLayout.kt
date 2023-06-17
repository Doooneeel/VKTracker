package ru.vktracker.feature.profile.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import ru.vktracker.R
import ru.vktracker.databinding.ItemLayoutBinding

/**
 * @author Danil Glazkov on 17.06.2023, 08:26
 */
class ItemLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
) : LinearLayoutCompat(
    context,
    attributeSet,
) {
    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.item_layout, this, true)
        val binding = ItemLayoutBinding.bind(this)

        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ItemLayout)

        binding.titleTextView.text = typedArray.getString(R.styleable.ItemLayout_titleText)
        binding.descriptionTextView.text = typedArray.getString(R.styleable.ItemLayout_descriptionText)
        binding.imageView.setImageDrawable(typedArray.getDrawable(R.styleable.ItemLayout_icon))

        typedArray.recycle()

    }

}