package com.josenromero.notesandmore.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.josenromero.notesandmore.ui.theme.NotesAndMoreTheme

@Composable
fun MyDialog(
    onDismissRequest: () -> Unit,
    confirm: () -> Unit,
    dismiss: () -> Unit,
    icon: ImageVector = Icons.Filled.Warning,
    title: String,
    text: String
) {

    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = { confirm() }) {
                Text(text = "Yes")
            }
        },
        dismissButton = {
            TextButton(onClick = { dismiss() }) {
                Text(text = "No")
            }
        },
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = "icon $title",
                tint = MaterialTheme.colorScheme.error
            )
        },
        title = { Text(text = title) },
        text = {
            Text(
                text = text,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        containerColor = MaterialTheme.colorScheme.surface
    )

}

@Composable
fun FakeMyDialog() {
    NotesAndMoreTheme {
        MyDialog(
            onDismissRequest = {},
            confirm = {},
            dismiss = {},
            title = "Delete note",
            text = "Are you sure?"
        )
    }
}

@Preview
@Composable
fun MyDialogPreview() {
    FakeMyDialog()
}