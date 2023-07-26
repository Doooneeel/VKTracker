package ru.vktracker.core.ui.view.image

import android.content.Context
import android.util.AttributeSet
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import ru.vktracker.core.ui.view.common.AbstractView
import ru.vktracker.core.ui.view.common.skeleton.ProvideSkeletonDeclaredStyle
import ru.vktracker.core.ui.view.common.skeleton.Skeleton

/**
 * @author Danil Glazkov on 02.06.2023, 12:18
 */
class ImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ShapeableImageView(context, attrs), AbstractView.ImageUrl {

    private val skeleton = Skeleton.Base(this)

    private val declaredStyle = when (attrs) {
        null -> ProvideSkeletonDeclaredStyle.Default()
        else -> ProvideSkeletonDeclaredStyle.Base(attrs)
    }.style(context)

    init {
        if (declaredStyle.enabled) skeleton.show()
    }

    override fun load(url: String) {
        Glide.with(this)
            .load(url)
            .centerCrop()
            .into(this)
    }
}