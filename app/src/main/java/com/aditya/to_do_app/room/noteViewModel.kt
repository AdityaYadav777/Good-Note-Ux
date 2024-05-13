package com.aditya.to_do_app.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class noteViewModel(application: Application) :AndroidViewModel(application) {


    val repo:NoteRepo

    init {
        val dao=NoteDabase.getDatabseInstance(application).myNotesDao()
       repo=NoteRepo(dao)

    }

    fun addNote(note:Note){
        repo.insertNotes(note)
    }

    fun getNotes():LiveData<List<Note>> =repo.getAllNotes()

    fun deleteNote(id:Int){
        repo.deleteNote(id)
    }

    fun updateNote(note:Note){
        repo.updataNote(note)
    }

    fun getLowNotes():LiveData<List<Note>>  =    repo.getLowNotes()

    fun getMediumNote():LiveData<List<Note>> = repo.getMediumNote()

    fun getHighNote():LiveData<List<Note>> = repo.getHighNote()



}