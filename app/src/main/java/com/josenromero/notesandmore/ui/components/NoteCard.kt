package com.josenromero.notesandmore.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.notesandmore.data.notes.NoteEntity
import com.josenromero.notesandmore.ui.theme.NotesAndMoreTheme

@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    note: NoteEntity,
    onSelectedNote: (note: NoteEntity) -> Unit
) {
    Card(
        modifier = modifier.clickable { onSelectedNote(note) },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = note.title,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black
            )
            Text(
                text = note.body,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black
            )
        }
    }
}

@Composable
fun FakeNoteCard() {
    NotesAndMoreTheme {
        val fakeNote = NoteEntity(0, "example title", "this is an example note")
        NoteCard(note = fakeNote, onSelectedNote = {})
    }
}

@Preview
@Composable
fun NoteCardPreview() {
    FakeNoteCard()
}
