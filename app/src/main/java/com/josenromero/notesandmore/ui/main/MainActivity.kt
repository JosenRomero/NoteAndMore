package com.josenromero.notesandmore.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.josenromero.notesandmore.ui.main.navigation.AppNavigation
import com.josenromero.notesandmore.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(Constants.spanishTag))
            AppNavigation()
        }
    }
}
