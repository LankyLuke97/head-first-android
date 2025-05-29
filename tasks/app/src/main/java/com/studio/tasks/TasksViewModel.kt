package com.studio.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TasksViewModel(val dao: TaskDao) : ViewModel() {
    var newTaskName = ""
    val tasks = dao.getAll()
    val tasksString = tasks.map { tasks -> formatTasks(tasks) }

    fun addTask() {
        viewModelScope.launch {
            val task = Task()
            task.taskName = newTaskName
            dao.insert(task)
        }
    }

    private fun formatTasks(tasks: List<Task>): String {
        return tasks.fold("") {
            str, item -> str + '\n' + formatTask(item)
        }
    }

    private fun formatTask(task: Task): String {
        return "ID: ${task.taskId}\n" +
               "Name: ${task.taskName}\n" +
               "Complete: ${task.taskDone}\n"
    }
}