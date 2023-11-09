package com.josenromero.notesandmore.ui.main.views

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.josenromero.notesandmore.R
import com.josenromero.notesandmore.data.notes.NoteEntity
import com.josenromero.notesandmore.ui.components.EmptyNoteList
import com.josenromero.notesandmore.ui.components.MyDialog
import com.josenromero.notesandmore.ui.components.NoteList
import com.josenromero.notesandmore.ui.theme.NotesAndMoreTheme
import com.josenromero.notesandmore.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrashScreen(
    onNavigateToBack: () -> Unit,
    trashedNotes: List<NoteEntity>,
    onSelectedNote: (note: NoteEntity) -> Unit,
    deleteTrashedNotes: () -> Unit
) {

    var menuExpanded by remember { mutableStateOf(false) }
    var isOpenDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                ),
                title = { Text(text = stringResource(id = R.string.trash)) },
                navigationIcon = {
                    IconButton(onClick = { onNavigateToBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_icon),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                actions = {
                    if(trashedNotes.isNotEmpty()) {
                        IconButton(onClick = { menuExpanded = !menuExpanded }) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = stringResource(id = R.string.more_icon),
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false },
                        modifier = Modifier.background(MaterialTheme.colorScheme.tertiaryContainer)
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = stringResource(id = R.string.empty_trash)) },
                            onClick = {
                                isOpenDialog = true
                                menuExpanded = false
                            }
                        )
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        if(trashedNotes.isEmpty()) {
            EmptyNoteList(stringResource(id = R.string.trash_screen_text_a1))
        }

        NoteList(modifier = Modifier.padding(it), notes = trashedNotes, onSelectedNote = onSelectedNote)

        if(isOpenDialog) {
            MyDialog(
                onDismissRequest = { isOpenDialog = false },
                confirm = {
                    deleteTrashedNotes()
                    isOpenDialog = false
                },
                dismiss = { isOpenDialog = false },
                title = stringResource(id = R.string.empty_trash_)
            )
        }

    }

}


@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TrashScreenPreview() {
    NotesAndMoreTheme {
        TrashScreen(
            onNavigateToBack = {},
            trashedNotes = Constants.fakeNotes,
            onSelectedNote = {},
            deleteTrashedNotes = {}
        )
    }
}
