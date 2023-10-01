package com.josenromero.notesandmore.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josenromero.notesandmore.ui.main.viewmodels.NoteViewModel
import com.josenromero.notesandmore.ui.main.views.AddScreen
import com.josenromero.notesandmore.ui.main.views.HomeScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val noteViewModel: NoteViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route) {
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(
                onNavigateToAddScreen = { navController.navigate(route = AppScreens.AddScreen.route) },
                notes = noteViewModel.notes.value
            )
        }
        composable(route = AppScreens.AddScreen.route) {
            AddScreen(onNavigateToBack = { navController.popBackStack() })
        }
    }

}
