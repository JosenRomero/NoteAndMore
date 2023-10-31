package com.josenromero.notesandmore.domain.userPreferences

import com.josenromero.notesandmore.data.userPreferences.UserPreferencesRepository
import javax.inject.Inject

class SetDarkTheme @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) {

    suspend operator fun invoke(value: Boolean) {
        userPreferencesRepository.setDarkTheme(value)
    }

}