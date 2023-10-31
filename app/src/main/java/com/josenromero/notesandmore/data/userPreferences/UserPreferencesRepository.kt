package com.josenromero.notesandmore.data.userPreferences

import javax.inject.Inject

class UserPreferencesRepository @Inject constructor(
    private val userPreferences: UserPreferences
) {

    suspend fun getDarkThem(): Boolean {
        return userPreferences.getDarkTheme()
    }

    suspend fun setDarkTheme(value: Boolean) {
        userPreferences.setDarkTheme(value)
    }

}