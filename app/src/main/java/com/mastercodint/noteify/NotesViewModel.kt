package com.mastercodint.noteify

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NotesViewModel(application: Application):AndroidViewModel(application) {
    val repository:NotsRepository

    init {
        val dao=NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository=NotsRepository(dao)
    }
    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }
    fun getNotes():LiveData<List<Notes>> = repository.getAllNotes()

    fun getLowNotes():LiveData<List<Notes>> = repository.getLowNotes()

    fun getHighNotes():LiveData<List<Notes>> = repository.getHighNotes()

    fun getMediumNotes():LiveData<List<Notes>> = repository.getMediumNotes()

    fun deleteNotes(id:Int){
        repository.deleteNotes(id)
    }
    fun updateNote(notes: Notes){
        repository.updateNotes(notes)
    }

}