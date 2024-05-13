package com.aditya.to_do_app.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDabase:RoomDatabase() {

    abstract fun myNotesDao():NoteDao

    companion object{
        @Volatile
        private var INSTANCE:NoteDabase?=null

        fun getDatabseInstance(context:Context):NoteDabase{

           val tempInstance= INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val roomDatabseInstance=Room.databaseBuilder(context,NoteDabase::class.java,"Notes").allowMainThreadQueries() .build()
                INSTANCE=roomDatabseInstance
                return return roomDatabseInstance
            }
        }
    }

}