package com.josenromero.notesandmore.data.notes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.josenromero.notesandmore.utils.Constants

// DAO - Data Access Object
@Dao
interface NoteDao {

    @Query("SELECT * FROM ${Constants.note_table} WHERE trashed = 0 ORDER BY uid ASC")
    fun getAllNotes(): List<NoteEntity>

    @Query("SELECT * FROM ${Constants.note_table} WHERE trashed = 1 ORDER BY uid ASC")
    fun getAllTrashedNotes(): List<NoteEntity>

    @Insert
    fun addOneNote(note: NoteEntity)

    @Delete
    fun deleteOneNote(note: NoteEntity)

    @Update
    fun updateOneNote(note: NoteEntity)

}