package com.josenromero.notesandmore.data.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.josenromero.notesandmore.utils.Constants

@Entity(tableName = Constants.note_table)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo (name = "title") val title: String = "",
    @ColumnInfo (name = "body") val body: String = "",
    @ColumnInfo (name = "trashed") val trashed: Int = 0
)
