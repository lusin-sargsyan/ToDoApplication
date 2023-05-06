package com.example.todoapplication.presentation.ui.home_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.data.repo.Repository
import com.example.todoapplication.domain.entities.Title
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class TitleViewModel(private val repository: Repository) : ViewModel() {

    val getAllTitles = MutableSharedFlow<Flow<List<Title>>>(1)

    fun addTitle(title: Title) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTitle(title)
        }
    }

    fun deleteTitle(title: Title) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTitle(title)
        }
    }

    fun getAllTitle() {
        viewModelScope.launch {
            getAllTitles.emit(repository.getAllTitle)
        }
    }
}