package com.josenromero.notesandmore.ui.main.navigation

sealed class AppScreens(val route: String) {
    object HomeScreen: AppScreens("HomeScreen")
    object AddScreen: AppScreens("AddScreen")
}
