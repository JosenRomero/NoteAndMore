package com.josenromero.notesandmore.data.userPreferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.josenromero.notesandmore.utils.Constants
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    private companion object {
        val key_darkTheme = booleanPreferencesKey(Constants.PREFERENCESKEY_darkTheme)
    }

    suspend fun getDarkTheme(): Boolean {
        return try {
            val preferences = dataStore.data.first()
            preferences[key_darkTheme] ?: false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun setDarkTheme(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[key_darkTheme] = value
        }
    }
}