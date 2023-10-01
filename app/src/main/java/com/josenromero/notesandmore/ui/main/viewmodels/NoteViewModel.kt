package com.josenromero.notesandmore.ui.main.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josenromero.notesandmore.data.notes.NoteEntity
import com.josenromero.notesandmore.domain.notes.AddOneNote
import com.josenromero.notesandmore.domain.notes.DeleteOneNote
import com.josenromero.notesandmore.domain.notes.GetAllNotes
import com.josenromero.notesandmore.domain.notes.UpdateOneNote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val getAllNotes: GetAllNotes,
    private val addOneNote: AddOneNote,
    private val updateOneNote: UpdateOneNote,
    private val deleteOneNote: DeleteOneNote
) : ViewModel() {

    private val _notes: MutableState<List<NoteEntity>> = mutableStateOf(emptyList())
    val notes: State<List<NoteEntity>> get() = _notes

    init {
        collectNotes()
    }

    private fun collectNotes() {
        viewModelScope.launch(Dispatchers.IO) {

            val allNotes = getAllNotes()

            Log.d("Notes", allNotes.toString())

            withContext(Dispatchers.Main) {
                _notes.value = allNotes
            }

        }
    }

    fun onAddOneNote(note: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            addOneNote(note)
            collectNotes()
        }
    }

}
