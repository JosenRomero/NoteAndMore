package com.josenromero.notesandmore.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.josenromero.notesandmore.ui.main.navigation.AppNavigation
import com.josenromero.notesandmore.ui.theme.NotesAndMoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAndMoreTheme {
                AppNavigation()
            }
        }
    }
}
