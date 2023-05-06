package com.example.todoapplication.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "toDos")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "user_id")
    val user_id: Long,
    @ColumnInfo(name = "toDo")
    val toDo: String,
    @ColumnInfo(name = "check")
    var check: Boolean
)
