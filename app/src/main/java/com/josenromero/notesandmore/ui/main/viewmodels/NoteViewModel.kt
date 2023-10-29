package com.josenromero.notesandmore.ui.main.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josenromero.notesandmore.data.notes.NoteEntity
import com.josenromero.notesandmore.domain.notes.AddOneNote
import com.josenromero.notesandmore.domain.notes.DeleteOneNote
import com.josenromero.notesandmore.domain.notes.DeleteTrashedNotes
import com.josenromero.notesandmore.domain.notes.GetAllNotes
import com.josenromero.notesandmore.domain.notes.GetAllTrashedNotes
import com.josenromero.notesandmore.domain.notes.UpdateOneNote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val getAllNotes: GetAllNotes,
    private val getAllTrashedNotes: GetAllTrashedNotes,
    private val addOneNote: AddOneNote,
    private val updateOneNote: UpdateOneNote,
    private val deleteOneNote: DeleteOneNote,
    private val deleteTrashedNotes: DeleteTrashedNotes
) : ViewModel() {

    private val _notes: MutableState<List<NoteEntity>> = mutableStateOf(emptyList())
    val notes: State<List<NoteEntity>> get() = _notes

    private val _trashedNotes: MutableState<List<NoteEntity>> = mutableStateOf(emptyList())
    val trashedNotes: State<List<NoteEntity>> get() = _trashedNotes

    private val _selectedNote: MutableState<NoteEntity> = mutableStateOf(NoteEntity())
    val selectedNote: State<NoteEntity> get() = _selectedNote

    private val _darkTheme: MutableState<Boolean> = mutableStateOf(false)
    val darkTheme: State<Boolean> get() = _darkTheme

    init {
        collectNotes()
        collectTrashedNotes()
    }

    private fun collectTrashedNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            val allTrashedNotes = getAllTrashedNotes()
            withContext(Dispatchers.Main) {
                _trashedNotes.value = allTrashedNotes
            }
        }
    }

    private fun collectNotes() {
        viewModelScope.launch(Dispatchers.IO) {

            val allNotes = getAllNotes()

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

    fun onUpdateOneNote(note: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            updateOneNote(note)
            collectNotes()
            collectTrashedNotes()
        }
    }

    fun onDeleteOneNote(note: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteOneNote(note)
            collectTrashedNotes()
        }
    }

    fun onDeleteTrashedNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteTrashedNotes()
            collectTrashedNotes()
        }
    }

    fun setSelectedNote(note: NoteEntity) {
        _selectedNote.value = note
    }

    fun setDarkTheme(value: Boolean) {
        _darkTheme.value = value
    }

}
