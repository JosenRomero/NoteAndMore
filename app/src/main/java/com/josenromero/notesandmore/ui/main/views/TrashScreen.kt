package com.josenromero.notesandmore.ui.main.views

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.josenromero.notesandmore.data.notes.NoteEntity
import com.josenromero.notesandmore.ui.components.EmptyNoteList
import com.josenromero.notesandmore.ui.components.MyDialog
import com.josenromero.notesandmore.ui.components.NoteList
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
                },
                actions = {
                    if(trashedNotes.isNotEmpty()) {
                        IconButton(onClick = { menuExpanded = !menuExpanded }) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "More icon",
                                tint = Color.White
                            )
                        }
                    }
                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false },
                        modifier = Modifier.background(MaterialTheme.colorScheme.background)
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = "Empty trash") },
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
            EmptyNoteList("The notes you delete will appear here.")
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
                title = "Empty trash",
                text = "Are you sure?"
            )
        }

    }

}


@Preview
@Composable
fun TrashScreenPreview() {
    TrashScreen(
        onNavigateToBack = {},
        trashedNotes = Constants.fakeNotes,
        onSelectedNote = {},
        deleteTrashedNotes = {}
    )
}
