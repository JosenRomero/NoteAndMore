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
import com.josenromero.notesandmore.ui.components.NoteList
import com.josenromero.notesandmore.ui.theme.NotesAndMoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToAddScreen: () -> Unit,
    notes: List<NoteEntity>
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.secondary,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Add") },
                icon = { Icon(imageVector = Icons.Filled.Add, contentDescription = "Add") },
                onClick = { onNavigateToAddScreen() },
                containerColor = MaterialTheme.colorScheme.primary
            )
        }
    ) {
        NoteList(modifier = Modifier.padding(it), notes = notes, onSelectedNote = {})
    }

}

@Composable
fun FakeHomeScreen() {
    NotesAndMoreTheme {
        HomeScreen(onNavigateToAddScreen = {}, notes = emptyList())
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    FakeHomeScreen()
}
