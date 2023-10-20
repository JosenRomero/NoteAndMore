package com.josenromero.notesandmore.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.josenromero.notesandmore.ui.theme.NotesAndMoreTheme

@Composable
fun MyDialog(
    onDismissRequest: () -> Unit,
    confirm: () -> Unit,
    dismiss: () -> Unit,
    title: String
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
        title = {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    )

}

@Preview(name = "Light Mode", showSystemUi = true)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun MyDialogPreview() {
    NotesAndMoreTheme {
        MyDialog(
            onDismissRequest = {},
            confirm = {},
            dismiss = {},
            title = "Delete note"
        )
    }
}
