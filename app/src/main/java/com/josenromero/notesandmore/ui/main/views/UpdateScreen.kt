package com.josenromero.notesandmore.ui.main.views

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
fun UpdateScreen(
    selectedNote: NoteEntity,
    onNavigateToBack: () -> Unit,
    updateOneNote: (note: NoteEntity) -> Unit,
    trashedOneNote: (note: NoteEntity) -> Unit
) {

    var title by remember { mutableStateOf(selectedNote.title) }
    var body by remember { mutableStateOf(selectedNote.body) }
    var isError by remember { mutableStateOf(false) }
    var isOpenDialog by remember { mutableStateOf(false) }
    var isDialogToSave by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                ),
                title = { Text(text = stringResource(id = R.string.update_a_note)) },
                navigationIcon = {
                    IconButton(onClick = {
                        if((selectedNote.title == title) && (selectedNote.body == body)) {
                            onNavigateToBack()
                        } else {
                            isDialogToSave = true
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_icon),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { isOpenDialog = true }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = stringResource(id = R.string.delete_icon),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    IconButton(onClick = {
                        if (title.trim().isEmpty()) isError = true
                        else updateOneNote(NoteEntity(selectedNote.uid, title, body))
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = stringResource(id = R.string.done_icon),
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
                    .imePadding()
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = title,
                    onValueChange = { newText -> title = newText },
                    label = { Text(text = stringResource(id = R.string.note_title)) },
                    isError = isError,
                    supportingText = {
                        if (isError) {
                            if (title.trim().isNotEmpty()) isError = false
                            Text(
                                text = stringResource(id = R.string.update_screen_text_a1),
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState(), reverseScrolling = true),
                    value = body,
                    onValueChange = { newText -> body = newText },
                    label = { Text(text = stringResource(id = R.string.note_body)) }
                )
                if (isOpenDialog) {
                    MyDialog(
                        onDismissRequest = { isOpenDialog = false },
                        confirm = {
                            trashedOneNote(selectedNote)
                            isOpenDialog = false
                        },
                        dismiss = { isOpenDialog = false },
                        title = stringResource(id = R.string.delete_note)
                    )
                }
                if(isDialogToSave) {
                    MyDialog(
                        onDismissRequest = { isDialogToSave = false },
                        confirm = {
                            if (title.trim().isEmpty()) isError = true
                            else updateOneNote(NoteEntity(selectedNote.uid, title, body))
                            isDialogToSave = false
                        },
                        dismiss = {
                            isDialogToSave = false
                            onNavigateToBack()
                        },
                        confirmText = stringResource(id = R.string.save),
                        dismissText = stringResource(id = R.string.discard),
                        title = stringResource(id = R.string.save_or_discard)
                    )
                }
            }
        }
    }

}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun UpdateScreenPreview() {
    NotesAndMoreTheme {
        UpdateScreen(
            selectedNote = Constants.fakeNotes[0],
            onNavigateToBack = {},
            updateOneNote = {},
            trashedOneNote = {}
        )
    }
}
