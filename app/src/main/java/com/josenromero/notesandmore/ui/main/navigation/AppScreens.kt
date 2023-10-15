package com.josenromero.notesandmore.ui.main.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen: AppScreens("SplashScreen")
    object HomeScreen: AppScreens("HomeScreen")
    object AddScreen: AppScreens("AddScreen")
    object UpdateScreen: AppScreens("UpdateScreen")
    object TrashScreen: AppScreens("TrashScreen")
}
