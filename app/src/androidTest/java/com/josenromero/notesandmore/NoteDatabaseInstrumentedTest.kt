package com.josenromero.notesandmore

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.josenromero.notesandmore.data.notes.NoteDao
import com.josenromero.notesandmore.data.notes.NoteDatabase
import com.josenromero.notesandmore.data.notes.NoteEntity
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class NoteDatabaseInstrumentedTest {

    private lateinit var noteDao: NoteDao
    private lateinit var db: NoteDatabase

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, NoteDatabase::class.java).build()
        noteDao = db.noteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeNoteAndReadAllNotes() {

        // instance NoteEntity
        val title = "viaje"
        val body = "No olvidar el cafe"
        val myFirstNote = NoteEntity(1, title, body)

        // add one note, get All Notes and delete one note in Database
        noteDao.addOneNote(note = myFirstNote)
        val notes = noteDao.getAllNotes()
        noteDao.deleteOneNote(note = myFirstNote)

        println(notes)

        // check myFirstNote in database
        assertEquals(notes[0].title, title)

    }

}