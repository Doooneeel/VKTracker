package ru.vktracker.core.ui.view

import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.elevation.SurfaceColors
import ru.vktracker.R

/**
 * @author Danil Glazkov on 17.06.2023, 19:07
 */
interface Skeleton {

    fun show(view: View)


    object Base : Skeleton {

        override fun show(view: View) {
            val context = view.context
            val color = SurfaceColors.SURFACE_1.getColor(context)

            AppCompatResources.getDrawable(context, R.drawable.skeleton).let { skeleton ->
                view.background = skeleton
                view.background.setTint(color)
            }
        }
    }

}