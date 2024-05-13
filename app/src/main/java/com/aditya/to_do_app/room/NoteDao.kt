package com.aditya.to_do_app.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
 fun insert(note:Note)

@Query("Delete from Notes where id=:id")
fun delete(id:Int)

    @Query("select *from Notes")
  fun getNotes():LiveData<List<Note>>


  @Update
  fun Update(note: Note)

@Query("Select * from Notes where priority=1")
  fun getLowNotes():LiveData<List<Note>>


    @Query("Select * from Notes where priority=2")
    fun getMediumNotes():LiveData<List<Note>>

    @Query("Select * from Notes where priority=3")
    fun getHighNotes():LiveData<List<Note>>
}