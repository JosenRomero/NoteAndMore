package com.josenromero.notesandmore.domain.notes

import com.josenromero.notesandmore.data.notes.NoteEntity
import com.josenromero.notesandmore.data.notes.NoteRepository
import javax.inject.Inject

class UpdateOneNote @Inject constructor(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(note: NoteEntity) {
        noteRepository.updateOneNote(note)
    }

}