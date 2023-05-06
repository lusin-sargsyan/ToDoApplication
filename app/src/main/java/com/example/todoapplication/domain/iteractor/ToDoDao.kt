package com.example.todoapplication.domain.iteractor

import androidx.room.*
import com.example.todoapplication.domain.entities.ToDo
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Insert
    suspend fun insertToDo(toDo: ToDo)

    @Delete
    suspend fun deleteToDo(toDo: ToDo)

    @Update
    suspend fun updateTodo(toDo: ToDo)

    @Update
    suspend fun editToDo(toDo: ToDo)

    @Query("SELECT * FROM toDos WHERE user_id = :userId")
    fun getAllTodo(userId: Long): Flow<List<ToDo>>
}