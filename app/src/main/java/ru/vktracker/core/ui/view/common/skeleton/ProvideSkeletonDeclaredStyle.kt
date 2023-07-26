package ru.vktracker.core.ui.view.common.skeleton

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import ru.vktracker.R
import ru.vktracker.core.ui.view.common.ProvideDeclaredStyle

/**
 * @author Danil Glazkov on 26.07.2023, 16:44
 */
interface ProvideSkeletonDeclaredStyle : ProvideDeclaredStyle<SkeletonDeclaredStyle> {

    class Default : ProvideSkeletonDeclaredStyle {
        override fun style(context: Context) = SkeletonDeclaredStyle(false)
    }

    class Base(
        attributeSet: AttributeSet,
    ) : ProvideDeclaredStyle.Abstract<SkeletonDeclaredStyle>(
        attributeSet
    ) , ProvideSkeletonDeclaredStyle {
        override val styleRes: IntArray = R.styleable.Skeleton

        override fun TypedArray.style(context: Context): SkeletonDeclaredStyle =
            SkeletonDeclaredStyle(getBoolean(R.styleable.Skeleton_enableSkeleton, false))
    }

}