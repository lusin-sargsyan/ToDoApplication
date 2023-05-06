package com.example.todoapplication.presentation.ui.list_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.data.repo.Repository
import com.example.todoapplication.domain.entities.Title
import com.example.todoapplication.domain.entities.ToDo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class ToDoViewModel(private val repository: Repository) : ViewModel() {

    val getAllToDos = MutableSharedFlow<Flow<List<ToDo>>>(1)
    val getTitle = MutableSharedFlow<Title>(1)


    fun getAllToDo(id: Long) {
        viewModelScope.launch {
            getAllToDos.emit(repository.getAllTodo(id))
        }
    }

    fun addTodo(toDo: ToDo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTodo(toDo)
        }
    }

    fun deleteTodo(toDo: ToDo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTodo(toDo)
        }
    }

    fun getTitle(id: Long) {
        viewModelScope.launch {
            getTitle.emit(repository.getTitle(id))
        }
    }

    fun updateTodo(toDo: ToDo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTodo(toDo)
        }
    }

    fun editTodo(toDo: ToDo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.editTodo(toDo)
        }
    }
}