package com.josenromero.notesandmore.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.notesandmore.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menu(
    notesTotal: Int,
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToTrashScreen: () -> Unit
) {

    ModalDrawerSheet {
        Column(
            modifier = Modifier
                .padding(NavigationDrawerItemDefaults.ItemPadding),
        ) {
            Row(
                modifier = Modifier.height(120.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Notes App",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge
                )
            }
            Divider()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.note),
                    contentDescription = "note icon",
                    modifier = Modifier.size(32.dp)
                )
                NavigationDrawerItem(
                    label = { Text(text = "All notes") },
                    selected = false,
                    onClick = { onNavigateToHomeScreen() },
                    badge = {
                        Text(text = notesTotal.toString())
                    }
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "delete icon",
                    modifier = Modifier.size(32.dp)
                )
                NavigationDrawerItem(
                    label = { Text(text = "Trash") }, 
                    selected = false, 
                    onClick = { onNavigateToTrashScreen() },
                    badge = {
                        Text(text = "0")
                    }
                )
            }
        }
    }

}

@Preview
@Composable
fun MenuPreview() {
    Menu(notesTotal = 5, onNavigateToHomeScreen = {}, onNavigateToTrashScreen = {})
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MenuDarkPreview() {
    Menu(notesTotal = 5, onNavigateToHomeScreen = {}, onNavigateToTrashScreen = {})
}
