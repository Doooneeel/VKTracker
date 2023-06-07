package ru.vktracker.core.ui.resources

import android.content.SharedPreferences

/**
 * @author Danil Glazkov on 04.06.2023, 01:46
 */
interface ProvidePreferences {
    fun preferences(fileName: String): SharedPreferences
}