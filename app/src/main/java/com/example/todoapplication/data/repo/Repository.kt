package com.example.todoapplication.data.repo

import com.example.todoapplication.domain.entities.Title
import com.example.todoapplication.domain.iteractor.TitleDao
import com.example.todoapplication.domain.entities.ToDo
import com.example.todoapplication.domain.iteractor.ToDoDao
import kotlinx.coroutines.flow.Flow

class Repository(
    private val titleDao: TitleDao,
    private val toDoDao: ToDoDao
) {

    val getAllTitle: Flow<List<Title>> = titleDao.getAllTitles()
    fun getAllTodo(id: Long): Flow<List<ToDo>> = toDoDao.getAllTodo(id)

    suspend fun insertTitle(title: Title) {
        titleDao.insertTitle(title)
    }

    suspend fun deleteTitle(title: Title) {
        titleDao.deleteTitle(title)
    }

    suspend fun getTitle(id: Long): Title {
        return titleDao.getTitle(id)
    }

    suspend fun insertTodo(toDo: ToDo) {
        toDoDao.insertToDo(toDo)
    }

    suspend fun deleteTodo(toDo: ToDo) {
        toDoDao.deleteToDo(toDo)
    }

    suspend fun updateTodo(toDo: ToDo) {
        toDoDao.updateTodo(toDo)
    }

    suspend fun editTodo(toDo: ToDo) {
        toDoDao.editToDo(toDo)
    }

}