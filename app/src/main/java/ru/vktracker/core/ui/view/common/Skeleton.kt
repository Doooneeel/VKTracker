package ru.vktracker.core.ui.view.common

import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.elevation.SurfaceColors
import ru.vktracker.R

/**
 * @author Danil Glazkov on 17.06.2023, 19:07
 */
interface Skeleton {

    fun show()
    fun hide()


    class Base(private val view: View) : Skeleton {

        private val drawable = AppCompatResources.getDrawable(view.context, R.drawable.skeleton)
        private val color = SurfaceColors.SURFACE_1.getColor(view.context)

        override fun show() {
            view.background = drawable
            view.background.setTint(color)
        }

        override fun hide() {
            if (view.background == drawable) {
                view.background = null
            }
        }
    }

}