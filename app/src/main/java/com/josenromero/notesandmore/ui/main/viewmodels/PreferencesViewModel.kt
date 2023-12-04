package com.josenromero.notesandmore.ui.main.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josenromero.notesandmore.domain.userPreferences.GetDarkTheme
import com.josenromero.notesandmore.domain.userPreferences.SetDarkTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreferencesViewModel @Inject constructor(
    private val getDarkTheme: GetDarkTheme,
    private val setDarkTheme: SetDarkTheme
) : ViewModel() {

    private val _darkTheme: MutableState<Boolean> = mutableStateOf(false)
    val darkTheme: State<Boolean> get() = _darkTheme

    init {
        getDarkThemeValue()
    }

    fun getDarkThemeValue() {
        viewModelScope.launch {
            _darkTheme.value = getDarkTheme()
        }
    }

    fun setDarkThemeValue(value: Boolean) {
        viewModelScope.launch {
            setDarkTheme(value)
            _darkTheme.value = value
        }
    }

}
