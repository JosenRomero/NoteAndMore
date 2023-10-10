package com.josenromero.notesandmore.ui.main.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.josenromero.notesandmore.data.notes.NoteEntity
import com.josenromero.notesandmore.ui.components.EmptyNoteList
import com.josenromero.notesandmore.ui.components.NoteList
import com.josenromero.notesandmore.ui.theme.NotesAndMoreTheme
import com.josenromero.notesandmore.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToAddScreen: () -> Unit,
    notes: List<NoteEntity>,
    onSelectedNote: (note: NoteEntity) -> Unit
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
        }
    ) {
        if (notes.isEmpty()) {
            EmptyNoteList()
        }
        NoteList(modifier = Modifier.padding(it), notes = notes, onSelectedNote = onSelectedNote)
    }

}

@Composable
fun FakeHomeScreen() {
    NotesAndMoreTheme {
        HomeScreen(onNavigateToAddScreen = {}, notes = Constants.fakeNotes, onSelectedNote = {})
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    FakeHomeScreen()
}
