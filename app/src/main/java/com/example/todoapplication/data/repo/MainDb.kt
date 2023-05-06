package com.example.todoapplication.data.repo

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todoapplication.data.Converters
import com.example.todoapplication.domain.entities.Title
import com.example.todoapplication.domain.iteractor.TitleDao
import com.example.todoapplication.domain.entities.ToDo
import com.example.todoapplication.domain.iteractor.ToDoDao

@Database(entities = [Title::class, ToDo::class], version = 1, exportSchema = false)

@TypeConverters(Converters::class)
abstract class MainDb : RoomDatabase() {

    abstract fun getTitleDao(): TitleDao
    abstract fun getToDoDao(): ToDoDao
}