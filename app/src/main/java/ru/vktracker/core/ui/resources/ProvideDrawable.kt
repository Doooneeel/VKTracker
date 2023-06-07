package ru.vktracker.core.ui.resources

import android.content.res.Resources.*
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import kotlin.jvm.Throws

/**
 * @author Danil Glazkov on 08.06.2023, 02:05
 */
interface ProvideDrawable {

    @Throws(NotFoundException::class)
    fun drawable(@DrawableRes id: Int): Drawable

}