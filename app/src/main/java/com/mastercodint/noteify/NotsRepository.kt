package com.mastercodint.noteify

import androidx.lifecycle.LiveData

class NotsRepository(val dao: NotesDao) {

    fun getAllNotes():LiveData<List<Notes>>{
        return dao.getNotes()
    }
    fun getLowNotes():LiveData<List<Notes>>{
        return dao.getLowNotes()
    }
    fun getHighNotes():LiveData<List<Notes>>{
        return dao.getHighNotes()
    }
    fun getMediumNotes():LiveData<List<Notes>>{
        return dao.getMediumNotes()
    }
    fun insertNotes(notes: Notes){
         dao.insertNotes(notes)
    }
    fun deleteNotes(id:Int){
         dao.deletNotes(id)
    }
    fun updateNotes(notes: Notes){
         dao.updatesNotes(notes)
    }
}