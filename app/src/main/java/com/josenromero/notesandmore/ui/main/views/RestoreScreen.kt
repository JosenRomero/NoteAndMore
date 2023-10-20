package com.josenromero.notesandmore.ui.main.views

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.notesandmore.data.notes.NoteEntity
import com.josenromero.notesandmore.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestoreScreen(
    selectedNote: NoteEntity,
    onNavigateToBack: () -> Unit,
    restoreOneNote: (note: NoteEntity) -> Unit,
    deleteOneNote: (note: NoteEntity) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                title = { Text(text = "Trash note") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateToBack() }) {
                        Icon(
                            imageVector = Icons.Filled.Close, 
                            contentDescription = "Back icon",
                            tint = Color.White
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        onClick = { restoreOneNote(selectedNote) }
                    ) {
                        Text(text = "Restore")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        onClick = {  }
                    ) {
                        Text(text = "Delete")
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = selectedNote.title,
                    readOnly = true,
                    onValueChange = {},
                    label = { Text(text = "Title") }
                )
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = selectedNote.body,
                    readOnly = true,
                    onValueChange = {},
                    label = { Text(text = "Body") }
                )
            }
        }
    }
}

@Preview
@Composable
fun RestoreScreenPreview() {
    RestoreScreen(
        selectedNote = Constants.fakeNotes[0],
        onNavigateToBack = {},
        restoreOneNote = {},
        deleteOneNote = {}
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun RestoreScreenDarkPreview() {
    RestoreScreen(
        selectedNote = Constants.fakeNotes[0],
        onNavigateToBack = {},
        restoreOneNote = {},
        deleteOneNote = {}
    )
}
