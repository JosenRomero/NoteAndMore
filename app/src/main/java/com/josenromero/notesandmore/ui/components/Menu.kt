package com.josenromero.notesandmore.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.notesandmore.R
import com.josenromero.notesandmore.ui.main.navigation.AppScreens
import com.josenromero.notesandmore.ui.theme.NotesAndMoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menu(
    notesTotal: Int,
    trashNotesTotal: Int,
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToAScreen: (route: String) -> Unit,
) {

    ModalDrawerSheet {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Menu_Background()

            Column(
                modifier = Modifier
                    .padding(NavigationDrawerItemDefaults.ItemPadding),
            ) {
                Row(
                    modifier = Modifier.height(100.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.notes_app),
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
                Spacer(modifier = Modifier.height(100.dp))
                NavigationDrawerItem(
                    label = { Text(text = stringResource(id = R.string.all_notes)) },
                    selected = false,
                    onClick = { onNavigateToHomeScreen() },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.note),
                            contentDescription = stringResource(id = R.string.note_icon),
                            modifier = Modifier.size(32.dp)
                        )
                    },
                    badge = { Text(text = notesTotal.toString()) }
                )
                NavigationDrawerItem(
                    label = { Text(text = stringResource(id = R.string.trash)) },
                    selected = false,
                    onClick = { onNavigateToAScreen(AppScreens.TrashScreen.route) },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = stringResource(id = R.string.delete_icon),
                            modifier = Modifier.size(32.dp)
                        )
                    },
                    badge = { Text(text = trashNotesTotal.toString()) }
                )
                Spacer(modifier = Modifier.padding(vertical = 15.dp))
                NavigationDrawerItem(
                    label = { Text(text = stringResource(id = R.string.settings)) },
                    selected = false,
                    onClick = { onNavigateToAScreen(AppScreens.SettingsScreen.route) },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = stringResource(id = R.string.settings_icon),
                            modifier = Modifier.size(32.dp)
                        )
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = stringResource(id = R.string.about)) },
                    selected = false,
                    onClick = { onNavigateToAScreen(AppScreens.AboutScreen.route) },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = stringResource(id = R.string.about_icon),
                            modifier = Modifier.size(32.dp)
                        )
                    }
                )
            }

        }
    }

}

@Composable
fun Menu_Background() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.menu_bgtop),
            contentDescription = stringResource(id = R.string.menu_bg_icon),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.FillBounds
        )
        Image(
            painter = painterResource(id = R.drawable.menu_bgbottom),
            contentDescription = stringResource(id = R.string.menu_bg_icon),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview(name = "Light Mode", showSystemUi = true)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun MenuPreview() {
    NotesAndMoreTheme {
        Menu(
            notesTotal = 5,
            trashNotesTotal = 2,
            onNavigateToHomeScreen = {},
            onNavigateToAScreen = {}
        )
    }
}
