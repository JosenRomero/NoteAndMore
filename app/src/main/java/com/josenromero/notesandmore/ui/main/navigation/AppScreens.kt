package com.josenromero.notesandmore.ui.main.navigation

sealed class AppScreens(val route: String) {
    object HomeScreen: AppScreens("HomeScreen")
    object AddScreen: AppScreens("AddScreen")
    object UpdateScreen: AppScreens("UpdateScreen")
    object TrashScreen: AppScreens("TrashScreen")
    object RestoreScreen: AppScreens("RestoreScreen")
    object AboutScreen: AppScreens("AboutScreen")
    object SettingsScreen: AppScreens("SettingsScreen")
    object LanguageScreen: AppScreens("LanguageScreen")
}
