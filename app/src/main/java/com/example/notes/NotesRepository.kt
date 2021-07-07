package com.example.notes

import androidx.lifecycle.LiveData

/**
 * The Repository pattern has more to do with how data is persisted and retrieved from the database,
 * while the ViewModel pattern is a UI pattern that defines how to bind data to the UI.
 * One is at the database level, while one is at the UI level .
 */

class NotesRepository(private val notesDao: NotesDao) {

    val  allNotes :LiveData<List<Notes>> = notesDao.getAllNotes()

    suspend fun insert(notes: Notes){
            notesDao.insert(notes)
    }

    suspend fun delete(notes: Notes){
        notesDao.delete(notes)
    }

}