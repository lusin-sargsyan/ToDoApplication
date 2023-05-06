package com.example.todoapplication.di

import android.content.Context
import androidx.room.Room
import com.example.todoapplication.data.repo.MainDb
import com.example.todoapplication.data.repo.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        Repository(
            getDatabase(androidContext()).getTitleDao(),
            getDatabase(androidContext()).getToDoDao()
        )
    }
}

@Volatile
private var INSTANCE: MainDb? = null
fun getDatabase(context: Context): MainDb {
    val tempInstance = INSTANCE
    if (tempInstance != null) {
        return tempInstance
    }
    synchronized(context.applicationContext) {
        val instance = Room.databaseBuilder(
            context.applicationContext,
            MainDb::class.java,
            "toDo_database"
        ).build()
        INSTANCE = instance
        return instance
    }
}