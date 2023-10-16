package com.josenromero.notesandmore.domain.notes

import com.josenromero.notesandmore.data.notes.NoteRepository
import javax.inject.Inject

class DeleteTrashedNotes @Inject constructor(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke() {
        noteRepository.deleteTrashedNotes()
    }

}