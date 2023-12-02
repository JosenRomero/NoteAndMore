package com.josenromero.notesandmore.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.josenromero.notesandmore.ui.main.navigation.AppNavigation
import com.josenromero.notesandmore.ui.main.viewmodels.NoteViewModel
import com.josenromero.notesandmore.ui.main.viewmodels.PreferencesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val preferencesViewModel by viewModels<PreferencesViewModel>()
    private val noteViewModel by viewModels<NoteViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                preferencesViewModel.isLoadingTheme && noteViewModel.isLoadingNotes
            }
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AppNavigation()
        }
    }
}
