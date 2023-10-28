package com.josenromero.notesandmore.ui.main.views

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.josenromero.notesandmore.data.notes.NoteEntity
import com.josenromero.notesandmore.ui.components.EmptyNoteList
import com.josenromero.notesandmore.ui.components.Menu
import com.josenromero.notesandmore.ui.components.NoteList
import com.josenromero.notesandmore.ui.theme.NotesAndMoreTheme
import com.josenromero.notesandmore.utils.Constants
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToAddScreen: () -> Unit,
    onNavigateToTrashScreen: () -> Unit,
    onNavigateToAboutScreen: () -> Unit,
    onNavigateToSettingsScreen: () -> Unit,
    notes: List<NoteEntity>,
    trashNotesTotal: Int,
    onSelectedNote: (note: NoteEntity) -> Unit
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Menu(
                notesTotal = notes.size,
                trashNotesTotal = trashNotesTotal,
                onNavigateToHomeScreen = { scope.launch { drawerState.close() } },
                onNavigateToTrashScreen = { onNavigateToTrashScreen() },
                onNavigateToAboutScreen = { onNavigateToAboutScreen() },
                onNavigateToSettingsScreen = { onNavigateToSettingsScreen() }
            )
        },
        scrimColor = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = { Text(text = "Add") },
                    icon = { Icon(imageVector = Icons.Filled.Add, contentDescription = "Add") },
                    onClick = { onNavigateToAddScreen() },
                    containerColor = MaterialTheme.colorScheme.primary
                )
            },
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        titleContentColor = MaterialTheme.colorScheme.onBackground
                    ),
                    title = { Text(text = "My notes") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Toggle drawer",
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                )
            }
        ) {
            if (notes.isEmpty()) {
                EmptyNoteList(text = "The notes you add will appear here.")
            }
            NoteList(
                modifier = Modifier.padding(it),
                notes = notes,
                onSelectedNote = onSelectedNote
            )
        }
    }

}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    NotesAndMoreTheme {
        HomeScreen(
            onNavigateToAddScreen = {},
            onNavigateToTrashScreen = {},
            onNavigateToAboutScreen = {},
            onNavigateToSettingsScreen = {},
            notes = Constants.fakeNotes,
            trashNotesTotal = 2,
            onSelectedNote = {}
        )
    }
}
