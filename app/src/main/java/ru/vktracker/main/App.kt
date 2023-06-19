package ru.vktracker.main

import android.app.Application
import com.google.android.material.color.DynamicColors
import com.vk.api.sdk.VK
import dagger.hilt.android.HiltAndroidApp

/**
 * @author Danil Glazkov on 08.06.2023, 03:39
 */
@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        VK.initialize(this)
        DynamicColors.applyToActivitiesIfAvailable(this)
    }

}