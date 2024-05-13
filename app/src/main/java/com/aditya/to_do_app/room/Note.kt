package com.aditya.to_do_app.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
class Note(
    @PrimaryKey
    val id: Int? = null,

    var title:String,

    var data:String,

    var date:String,

    var priority:String
)



