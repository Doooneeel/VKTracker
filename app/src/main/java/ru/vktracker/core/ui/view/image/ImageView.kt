package ru.vktracker.core.ui.view.image

import android.content.Context
import android.util.AttributeSet
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import ru.vktracker.R
import ru.vktracker.core.ui.view.common.AbstractView

/**
 * @author Danil Glazkov on 02.06.2023, 12:18
 */
class ImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ShapeableImageView(
    context,
    attrs,
    defStyleAttr
) , AbstractView.ImageUrl {

    override fun load(url: String) {
        if (url.isNotEmpty()) {
            Glide.with(this)
                .load(url)
                .placeholder(R.drawable.skeleton)
                .centerCrop()
                .into(this)
        }
    }
}