package com.josenromero.notesandmore.domain.notes

import com.josenromero.notesandmore.data.notes.NoteEntity
import com.josenromero.notesandmore.data.notes.NoteRepository
import javax.inject.Inject

class GetAllTrashedNotes @Inject constructor(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(): List<NoteEntity> {
        return noteRepository.getAllTrashedNotes()
    }

}