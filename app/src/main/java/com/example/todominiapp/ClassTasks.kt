package com.example.todominiapp

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tasks")
data class Tasks(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var title : String,
    var disc: String,
    var isDone: Boolean

)