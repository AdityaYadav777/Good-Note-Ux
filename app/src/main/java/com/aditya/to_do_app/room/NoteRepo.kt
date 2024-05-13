package com.aditya.to_do_app.room

import androidx.lifecycle.LiveData

class NoteRepo(private val noteDao: NoteDao) {

    fun getAllNotes():LiveData<List<Note>>{

        return noteDao.getNotes()
    }

    fun insertNotes(note: Note){

        noteDao.insert(note)
    }

    fun deleteNote(id : Int){

        noteDao.delete(id)
    }

    fun updataNote(note: Note){
        noteDao.Update(note)
    }

fun getLowNotes():LiveData<List<Note>>  =    noteDao.getLowNotes()
    fun getMediumNote():LiveData<List<Note>> = noteDao.getMediumNotes()

    fun getHighNote():LiveData<List<Note>> = noteDao.getHighNotes()
}