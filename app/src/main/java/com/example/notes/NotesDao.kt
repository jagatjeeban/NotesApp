package com.example.notes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {

    /**
     * onConflict is useful to know whether to abort or ignore or replace the new data
     * when we try to insert the same data again
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note:Notes)

    @Delete
    suspend fun delete(note: Notes)

    @Query("SELECT * FROM notes_table ORDER BY id DESC")
    fun getAllNotes():LiveData<List<Notes>>
}