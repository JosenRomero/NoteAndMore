package com.josenromero.notesandmore.data.notes

import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) {

    fun getAllNotes(): List<NoteEntity> {
        return noteDao.getAllNotes()
    }

    fun getAllTrashedNotes(): List<NoteEntity> {
        return noteDao.getAllTrashedNotes()
    }

    fun addOneNote(note: NoteEntity) {
        noteDao.addOneNote(note)
    }

    fun deleteOneNote(note: NoteEntity) {
        noteDao.deleteOneNote(note)
    }

    fun updateOneNote(note: NoteEntity) {
        noteDao.updateOneNote(note)
    }

}