package com.josenromero.notesandmore.ui.main.views

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import com.josenromero.notesandmore.R
import com.josenromero.notesandmore.ui.theme.NotesAndMoreTheme
import com.josenromero.notesandmore.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageScreen(
    onNavigateToBack: () -> Unit
) {

    val language: String = getCurrentLanguage()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                ),
                title = { Text(text = stringResource(id = R.string.select_language)) },
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
            ) {
                NavigationDrawerItem(
                    label = { Text(text = stringResource(id = R.string.english)) },
                    selected = false,
                    onClick = {
                        changeLanguage(Constants.englishTag)
                    },
                    badge = {
                        if (language == Constants.englishTag) {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = stringResource(id = R.string.done_icon),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = stringResource(id = R.string.spanish)) },
                    selected = false,
                    onClick = {
                        changeLanguage(Constants.spanishTag)
                    },
                    badge = {
                        if (language == Constants.spanishTag) {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = stringResource(id = R.string.done_icon),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                )
            }
        }
    }

}

fun changeLanguage(localeTag: String) {
    AppCompatDelegate.setApplicationLocales(
        LocaleListCompat.forLanguageTags(localeTag)
    )
}

fun getCurrentLanguage(): String {
    return AppCompatDelegate.getApplicationLocales().get(0)?.language ?: getDefaultLanguage()
}

fun getDefaultLanguage(): String {
    val systemLanguage: String = Locale.current.language
    var language = Constants.englishTag
    if (systemLanguage == Constants.spanishTag) language = systemLanguage
    return language
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun LanguageScreenPreview() {
    NotesAndMoreTheme {
        LanguageScreen(
            onNavigateToBack = {}
        )
    }
}
