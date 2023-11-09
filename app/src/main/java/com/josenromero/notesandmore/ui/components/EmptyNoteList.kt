package com.josenromero.notesandmore.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.notesandmore.R
import com.josenromero.notesandmore.ui.theme.NotesAndMoreTheme

@Composable
fun EmptyNoteList(
    text: String
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(id = R.string.logo_icon),
                modifier = Modifier.size(64.dp)
            )
            Text(text = stringResource(id = R.string.no_notes))
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = text)
        }
    }

}

@Preview(name = "Light Mode", showSystemUi = true)
@Composable
fun EmptyNoteListPreview() {
    NotesAndMoreTheme {
        EmptyNoteList(stringResource(id = R.string.home_screen_text_a1))
    }
}
