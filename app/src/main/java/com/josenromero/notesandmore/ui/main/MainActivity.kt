package com.josenromero.notesandmore.ui.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.josenromero.notesandmore.ui.main.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var keepSplashOnScreen: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                keepSplashOnScreen
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({ keepSplashOnScreen = false }, 3000L)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AppNavigation()
        }
    }
}
