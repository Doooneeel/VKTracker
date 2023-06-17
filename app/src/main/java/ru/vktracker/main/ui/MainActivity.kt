package ru.vktracker.main.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.android.material.elevation.SurfaceColors
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiConfig
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.vk.api.sdk.auth.VKScope
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO make authorization
        val preferences = getSharedPreferences("name", MODE_PRIVATE)
        val token = preferences.getString("key", "") ?: ""
        val secret = preferences.getString("secret", "") ?: ""

        if (token.isNotEmpty()) {
            VK.setConfig(
                VKApiConfig(
                    context = this,
                    appId = resources.getInteger(R.integer.com_vk_sdk_AppId),
                    accessToken = lazyOf(token),
                    langProvider = { "ru" },
                    secret = lazyOf(secret)
                )
            )
        } else {
            VK.login(this) {
                when (it) {
                    is VKAuthenticationResult.Success -> {
                        preferences.edit()
                            .putString("key", it.token.accessToken)
                            .putString("secret", it.token.secret)
                            .apply()
                    }

                    is VKAuthenticationResult.Failed -> Log.d("TTTT", "AUTH_ERROR")
                }
            }.launch(listOf(VKScope.FRIENDS, VKScope.WALL))
        }

        Log.d("TTTT", "auth = " + VK.isLoggedIn().toString())

        setContentView(R.layout.activity_main)

        WindowCompat.setDecorFitsSystemWindows(window, true)
        window.navigationBarColor = SurfaceColors.SURFACE_2.getColor(this)
    }
}