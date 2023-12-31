package com.josenromero.notesandmore.ui.main.views

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.josenromero.notesandmore.R
import com.josenromero.notesandmore.ui.components.SettingItem
import com.josenromero.notesandmore.ui.main.navigation.AppScreens
import com.josenromero.notesandmore.ui.theme.NotesAndMoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateToBack: () -> Unit,
    onNavigateToAScreen: (route: String) -> Unit,
    setDarkTheme: (value: Boolean) -> Unit,
    darkTheme: Boolean
) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                ),
                title = { Text(text = stringResource(id = R.string.settings)) },
                navigationIcon = {
                    IconButton(onClick = { onNavigateToBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_icon),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            SettingItem(
                text = stringResource(id = R.string.dark_mode),
                onClick = { }
            ) {
                Switch(
                    checked = darkTheme,
                    onCheckedChange = { value ->
                        setDarkTheme(value)
                    },
                    thumbContent = {
                        if (darkTheme) {
                            Icon(
                                painter = painterResource(id = R.drawable.moon),
                                contentDescription = stringResource(id = R.string.moon_icon),
                                tint = Color.White,
                                modifier = Modifier.size(SwitchDefaults.IconSize)
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.sun),
                                contentDescription = stringResource(id = R.string.sun_icon),
                                tint = Color.White,
                                modifier = Modifier.size(SwitchDefaults.IconSize)
                            )
                        }
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.primary,
                        checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                        uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                        uncheckedTrackColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            }
            SettingItem(
                text = stringResource(id = R.string.language),
                onClick = { onNavigateToAScreen(AppScreens.LanguageScreen.route) }
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = stringResource(id = R.string.arrow_icon)
                )
            }
        }
    }

}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SettingsScreenPreview() {
    NotesAndMoreTheme {
        SettingsScreen(
            onNavigateToBack = {},
            onNavigateToAScreen = {},
            setDarkTheme = {},
            darkTheme = false
        )
    }
}
