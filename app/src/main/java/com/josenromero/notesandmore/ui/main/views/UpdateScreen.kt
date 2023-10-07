package com.josenromero.notesandmore.ui.main.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.notesandmore.data.notes.NoteEntity
import com.josenromero.notesandmore.ui.components.MyDialog
import com.josenromero.notesandmore.ui.theme.NotesAndMoreTheme
import com.josenromero.notesandmore.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreen(
    selectedNote: NoteEntity,
    onNavigateToBack: () -> Unit,
    updateOneNote: (note: NoteEntity) -> Unit,
    deleteOneNote: (note: NoteEntity) -> Unit
) {

    var title by remember { mutableStateOf(selectedNote.title) }
    var body by remember { mutableStateOf(selectedNote.body) }
    var isError by remember { mutableStateOf(false) }
    var isOpenDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                title = { Text(text = "Update a note") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateToBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { isOpenDialog = true }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {
                        if (title.trim().isEmpty()) isError = true
                        else updateOneNote(NoteEntity(selectedNote.uid, title, body))
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Update",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
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
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = title,
                    onValueChange = { newText -> title = newText },
                    label = { Text(text = "Title") },
                    supportingText = {
                        if (isError) {
                            if (title.trim().isNotEmpty()) isError = false
                            Text(
                                text = "Note title can't be empty",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    value = body,
                    onValueChange = { newText -> body = newText },
                    label = { Text(text = "Body") }
                )
                if (isOpenDialog) {
                    MyDialog(
                        onDismissRequest = { isOpenDialog = false },
                        confirm = {
                            deleteOneNote(selectedNote)
                            isOpenDialog = false
                        },
                        dismiss = { isOpenDialog = false },
                        title = "Delete Note",
                        text = "Are you sure?",
                    )
                }
            }
        }
    }

}

@Composable
fun FakeUpdateScreen() {
    NotesAndMoreTheme {
        UpdateScreen(
            selectedNote = Constants.fakeNotes[0],
            onNavigateToBack = {},
            updateOneNote = {},
            deleteOneNote = {})
    }
}

@Preview
@Composable
fun UpdateScreenPreview() {
    FakeUpdateScreen()
}