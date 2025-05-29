package com.studio.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TasksViewModelFactory(private val dao: TaskDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasksViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TasksViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}