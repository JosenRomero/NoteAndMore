package com.josenromero.notesandmore.ui.main.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.josenromero.notesandmore.data.notes.NoteEntity
import com.josenromero.notesandmore.ui.components.EmptyNoteList
import com.josenromero.notesandmore.ui.components.NoteList
import com.josenromero.notesandmore.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrashScreen(
    onNavigateToBack: () -> Unit,
    trashedNotes: List<NoteEntity>,
    onSelectedtrashedNote: (note: NoteEntity) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                title = { Text(text = "Trash") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateToBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        if(trashedNotes.isEmpty()) {
            EmptyNoteList("The notes you delete will appear here.")
        }
        NoteList(modifier = Modifier.padding(it), notes = trashedNotes, onSelectedNote = onSelectedtrashedNote)
    }

}


@Preview
@Composable
fun TrashScreenPreview() {
    TrashScreen(
        onNavigateToBack = {},
        trashedNotes = Constants.fakeNotes,
        onSelectedtrashedNote = {}
    )
}
