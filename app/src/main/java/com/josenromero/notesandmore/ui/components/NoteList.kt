package com.josenromero.notesandmore.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.notesandmore.data.notes.NoteEntity
import com.josenromero.notesandmore.ui.theme.NotesAndMoreTheme
import com.josenromero.notesandmore.utils.Constants

@Composable
fun NoteList(
    modifier: Modifier = Modifier,
    notes: List<NoteEntity>,
    onSelectedNote: (note: NoteEntity) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(notes) { note ->
            NoteCard(
                modifier = Modifier
                    .padding(10.dp)
                    .height(100.dp)
                    .fillMaxWidth(),
                note = note,
                onSelectedNote = onSelectedNote
            )
        }
    }
}

@Preview(name = "Light Mode", showSystemUi = true)
@Composable
fun NoteListPreview() {
    NotesAndMoreTheme {
        NoteList(notes = Constants.fakeNotes, onSelectedNote = {})
    }
}
