package ru.vktracker.core.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

/**
 * @author Danil Glazkov on 02.06.2023, 12:18
 */
class ImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatImageView(
    context,
    attrs
) , AbstractView.ImageUrl {
    override fun load(url: String) {
        Glide.with(this)
            .load(url)
            .centerCrop()
            .into(this)
    }
}