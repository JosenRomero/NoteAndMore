package com.josenromero.notesandmore.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josenromero.notesandmore.data.notes.NoteEntity
import com.josenromero.notesandmore.ui.main.viewmodels.NoteViewModel
import com.josenromero.notesandmore.ui.main.views.AddScreen
import com.josenromero.notesandmore.ui.main.views.HomeScreen
import com.josenromero.notesandmore.ui.main.views.UpdateScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val noteViewModel: NoteViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route) {
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(
                onNavigateToAddScreen = { navController.navigate(route = AppScreens.AddScreen.route) },
                notes = noteViewModel.notes.value,
                onSelectedNote = {note ->
                    navController.navigate(route = AppScreens.UpdateScreen.route)
                }
            )
        }
        composable(route = AppScreens.AddScreen.route) {
            AddScreen(
                onNavigateToBack = { navController.popBackStack() },
                addOneNote = { note ->
                    noteViewModel.onAddOneNote(note)
                    navController.navigate(route = AppScreens.HomeScreen.route)
                })
        }
        composable(route = AppScreens.UpdateScreen.route) {
            UpdateScreen(
                selectedNote = NoteEntity(1, "aaaaa", "aabbbbbb"),
                onNavigateToBack = { navController.popBackStack() },
                updateOneNote = {note ->
                    noteViewModel.onUpdateOneNote(note)
                    navController.navigate(route = AppScreens.HomeScreen.route)
                }
            )
        }
    }

}
