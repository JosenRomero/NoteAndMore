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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.notesandmore.R
import com.josenromero.notesandmore.data.notes.NoteEntity
import com.josenromero.notesandmore.ui.components.MyDialog
import com.josenromero.notesandmore.ui.theme.NotesAndMoreTheme
import com.josenromero.notesandmore.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestoreScreen(
    selectedNote: NoteEntity,
    onNavigateToBack: () -> Unit,
    restoreOneNote: (note: NoteEntity) -> Unit,
    deleteOneNote: (note: NoteEntity) -> Unit
) {

    var isOpenDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                ),
                title = { Text(text = stringResource(id = R.string.trash_note)) },
                navigationIcon = {
                    IconButton(onClick = { onNavigateToBack() }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = stringResource(id = R.string.back_icon),
                            tint = MaterialTheme.colorScheme.onBackground
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
                        Text(text = stringResource(id = R.string.restore))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        onClick = { isOpenDialog = true }
                    ) {
                        Text(text = stringResource(id = R.string.delete))
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = selectedNote.title,
                    readOnly = true,
                    onValueChange = {},
                    label = { Text(text = stringResource(id = R.string.note_title)) }
                )
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = selectedNote.body,
                    readOnly = true,
                    onValueChange = {},
                    label = { Text(text = stringResource(id = R.string.note_body)) }
                )
                if(isOpenDialog) {
                    MyDialog(
                        onDismissRequest = { isOpenDialog = false },
                        confirm = {
                            deleteOneNote(selectedNote)
                            isOpenDialog = false
                        },
                        dismiss = { isOpenDialog = false },
                        title = stringResource(id = R.string.delete_note)
                    )
                }
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun RestoreScreenPreview() {
    NotesAndMoreTheme {
        RestoreScreen(
            selectedNote = Constants.fakeNotes[0],
            onNavigateToBack = {},
            restoreOneNote = {},
            deleteOneNote = {}
        )
    }
}
