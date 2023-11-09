package com.josenromero.notesandmore.ui.main.views

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import com.josenromero.notesandmore.ui.theme.NotesAndMoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    onNavigateToBack: () -> Unit,
    addOneNote: (note: NoteEntity) -> Unit
) {

    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                ),
                title = { Text(text = stringResource(id = R.string.add_a_note)) },
                navigationIcon = {
                    IconButton(onClick = { onNavigateToBack() }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = stringResource(id = R.string.back_icon),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (title.trim().isEmpty()) isError = true
                        else addOneNote(NoteEntity(0, title, body))
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = stringResource(id = R.string.save_icon),
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
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = title,
                    onValueChange = { newText -> title = newText },
                    label = { Text(text = stringResource(id = R.string.note_title)) },
                    supportingText = {
                        if (isError) {
                            if (title.trim().isNotEmpty()) isError = false
                            Text(text = stringResource(id = R.string.add_a_title), color = MaterialTheme.colorScheme.error)
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
                    label = { Text(text = stringResource(id = R.string.note_details)) }
                )
            }
        }
    }

}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AddScreenPreview() {
    NotesAndMoreTheme {
        AddScreen(onNavigateToBack = {}, addOneNote = {})
    }
}
