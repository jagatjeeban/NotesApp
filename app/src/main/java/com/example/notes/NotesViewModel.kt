package com.example.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(
    database:NotesDao, application: Application):AndroidViewModel(application){

    val allNotes :LiveData<List<Notes>>
    private val repository = NotesRepository(database)

    init {
        allNotes = repository.allNotes
    }

    fun onInsertNote(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(notes)
        }
    }
    fun onDeleteNote(notes: Notes){
        viewModelScope.launch(Dispatchers.IO){
            repository.delete(notes)

        }
    }
}