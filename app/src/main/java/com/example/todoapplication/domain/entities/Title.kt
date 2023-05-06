package com.example.todoapplication.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "titles", indices = [
        Index("title", unique = true)
    ]
)
data class Title(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "todoList")
    val todoList: List<ToDo>
)
