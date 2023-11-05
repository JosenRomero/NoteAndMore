package com.josenromero.notesandmore.utils

import com.josenromero.notesandmore.data.notes.NoteEntity

object Constants {
    const val PREFERENCESKEY_darkTheme= "key_darkTheme"
    const val PREFERENCES_DATASTORE = "user_data"
    const val note_database = "note_database"
    const val note_table = "note_table"

    const val englishTag = "en"
    const val spanishTag = "es"

    var fakeNotes = listOf<NoteEntity>(
        NoteEntity(0, "example 1 title", "this is an example note"),
        NoteEntity(0, "example 2 title", "this is an example note"),
        NoteEntity(0, "example 3 title", "this is an example note")
    )

}