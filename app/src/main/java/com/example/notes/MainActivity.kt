package com.example.notes

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NotesRVAdapter.INotesRVAdapter {

    private lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = NotesRVAdapter(this, this)
        recyclerView.adapter = adapter

        //Get a reference of application
        val application = requireNotNull(this).application

        val dataSource = NotesDatabase.getInstance(application).notesDao

        val viewModelFactory = NotesViewModelFactory(dataSource, application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(NotesViewModel::class.java)

        viewModel.allNotes.observe(this, { list ->
            if (list != null) {
                adapter.updateNoteList(list)
            }
        })
    }

    override fun onItemClicked(notes: Notes) {
        viewModel.onDeleteNote(notes)
        Toast.makeText(this, "Successfully deleted !", Toast.LENGTH_LONG).show()
    }

    fun addData(view: View) {
        val noteText = add_note.text.toString()

        if (noteText.isNotEmpty()) {
            viewModel.onInsertNote(Notes(noteText))
            add_note.text = null
            Toast.makeText(this, "Successfully added !", Toast.LENGTH_LONG).show()

        } else {
            Toast.makeText(this, "Enter note !", Toast.LENGTH_LONG).show()
        }
    }
}