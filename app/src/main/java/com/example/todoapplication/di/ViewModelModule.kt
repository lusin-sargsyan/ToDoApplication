package com.example.todoapplication.di

import com.example.todoapplication.presentation.ui.home_page.TitleViewModel
import com.example.todoapplication.presentation.ui.list_page.ToDoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        TitleViewModel(get())
    }

    viewModel {
        ToDoViewModel(get())
    }
}