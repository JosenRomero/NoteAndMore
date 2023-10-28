package com.josenromero.notesandmore.ui.main.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josenromero.notesandmore.data.notes.NoteEntity
import com.josenromero.notesandmore.ui.main.viewmodels.NoteViewModel
import com.josenromero.notesandmore.ui.main.views.AboutScreen
import com.josenromero.notesandmore.ui.main.views.AddScreen
import com.josenromero.notesandmore.ui.main.views.HomeScreen
import com.josenromero.notesandmore.ui.main.views.RestoreScreen
import com.josenromero.notesandmore.ui.main.views.SettingsScreen
import com.josenromero.notesandmore.ui.main.views.SplashScreen
import com.josenromero.notesandmore.ui.main.views.TrashScreen
import com.josenromero.notesandmore.ui.main.views.UpdateScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val noteViewModel: NoteViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route) {
        composable(route = AppScreens.SplashScreen.route) {
            SplashScreen(
                onNavigateToHomeScreen = {
                    navController.popBackStack()
                    navController.navigate(route = AppScreens.HomeScreen.route)
                }
            )
        }
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(
                onNavigateToAScreen = { route -> navController.navigate(route) },
                notes = noteViewModel.notes.value,
                trashNotesTotal = noteViewModel.trashedNotes.value.size,
                onSelectedNote = { note ->
                    noteViewModel.setSelectedNote(note)
                    navController.navigate(route = AppScreens.UpdateScreen.route)
                }
            )
        }
        composable(
            route = AppScreens.AddScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(700)
                )
            }
        ) {
            AddScreen(
                onNavigateToBack = { navController.popBackStack() },
                addOneNote = { note ->
                    noteViewModel.onAddOneNote(note)
                    navController.navigate(route = AppScreens.HomeScreen.route)
                })
        }
        composable(
            route = AppScreens.UpdateScreen.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }
        ) {
            UpdateScreen(
                selectedNote = noteViewModel.selectedNote.value,
                onNavigateToBack = { navController.popBackStack() },
                updateOneNote = { note ->
                    noteViewModel.onUpdateOneNote(note)
                    navController.navigate(route = AppScreens.HomeScreen.route)
                },
                trashedOneNote = { note ->
                    noteViewModel.onUpdateOneNote(NoteEntity(note.uid, note.title, note.body, 1))
                    navController.navigate(route = AppScreens.HomeScreen.route)
                }
            )
        }
        composable(route = AppScreens.TrashScreen.route) {
            TrashScreen(
                onNavigateToBack = { navController.popBackStack() },
                trashedNotes = noteViewModel.trashedNotes.value,
                onSelectedNote = {note ->
                    noteViewModel.setSelectedNote(note)
                    navController.navigate(route = AppScreens.RestoreScreen.route)
                },
                deleteTrashedNotes = { noteViewModel.onDeleteTrashedNotes() }
            )
        }
        composable(
            route = AppScreens.RestoreScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(700)
                )
            }
        ) {
            RestoreScreen(
                selectedNote = noteViewModel.selectedNote.value,
                onNavigateToBack = { navController.popBackStack() },
                restoreOneNote = {note ->
                    noteViewModel.onUpdateOneNote(NoteEntity(note.uid, note.title, note.body, 0))
                    navController.popBackStack()
                },
                deleteOneNote = {note ->
                    noteViewModel.onDeleteOneNote(note)
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = AppScreens.AboutScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(700)
                )
            }
        ) {
            AboutScreen(
                onNavigateToBack = { navController.popBackStack() }
            )
        }
        composable(
            route = AppScreens.SettingsScreen.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }
        ) {
            SettingsScreen(
                onNavigateToBack = { navController.popBackStack() }
            )
        }
    }

}
