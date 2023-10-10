package com.josenromero.notesandmore.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.notesandmore.R
import com.josenromero.notesandmore.ui.theme.NotesAndMoreTheme

@Composable
fun EmptyNoteList() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(painter = painterResource(id = R.drawable.note), contentDescription = "icon")
            Text(text = "No notes")
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "The notes you add will appear here.")
        }
    }

}

@Composable
fun FakeEmptyNoteList() {
    NotesAndMoreTheme {
        EmptyNoteList()
    }
}

@Preview
@Composable
fun EmptyNoteListPreview() {
    FakeEmptyNoteList()
}
