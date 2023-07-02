package ru.vktracker.core.ui.resources

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources

/**
 * @author Danil Glazkov on 04.06.2023, 01:39
 */
interface ManageResources : ProvideString.Combine, ProvideNumber, ProvideColor, ProvidePreferences,
    ProvideDrawable
{

    class Base(private val context: Context) : ManageResources {

        override fun drawable(id: Int): Drawable =
            AppCompatResources.getDrawable(context, id) ?: throw Resources.NotFoundException()

        override fun string(id: Int, vararg formatArgs: Any): String =
            context.getString(id, *formatArgs)

        override fun quantityString(id: Int, quantity: Int, vararg formatArgs: Any): String =
            context.resources.getQuantityString(id, quantity, *formatArgs)

        override fun stringList(id: Int): List<String> =
            context.resources.getStringArray(id).toList()

        override fun number(id: Int): Int = context.resources.getInteger(id)

        override fun color(id: Int): Int = context.getColor(id)

        override fun preferences(fileName: String): SharedPreferences =
            context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    }
}