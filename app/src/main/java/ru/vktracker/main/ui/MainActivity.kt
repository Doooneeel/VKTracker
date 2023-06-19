package ru.vktracker.main.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.android.material.elevation.SurfaceColors
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //todo make authorization
        if (!VK.isLoggedIn()) {
            VK.login(this) {}.launch(listOf(VKScope.FRIENDS, VKScope.WALL))
        }

        setContentView(R.layout.activity_main)

        WindowCompat.setDecorFitsSystemWindows(window, true)
        window.navigationBarColor = SurfaceColors.SURFACE_2.getColor(this)
    }
}