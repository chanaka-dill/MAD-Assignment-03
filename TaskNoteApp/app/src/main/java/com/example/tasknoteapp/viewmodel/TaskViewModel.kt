package com.example.tasknoteapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.tasknoteapp.data.TaskRepository
import com.example.tasknoteapp.model.Task
import java.util.UUID

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = TaskRepository(application.applicationContext)

    val tasks = MutableLiveData<MutableList<Task>>(mutableListOf())

    init {
        tasks.value = repo.loadTasks()
    }

    fun addTask(title: String, description: String?) {
        val list = tasks.value ?: mutableListOf()

        val task = Task(
            id = UUID.randomUUID().toString(),
            title = title.trim(),
            description = description?.takeIf { it.isNotBlank() },
            isCompleted = false
        )

        list.add(task)
        tasks.value = list
        repo.saveTasks(list)
    }

    fun deleteTask(task: Task) {
        val list = tasks.value ?: return
        list.removeAll { it.id == task.id }
        tasks.value = list
        repo.saveTasks(list)
    }

    fun toggleCompleted(task: Task) {
        val list = tasks.value ?: return
        val index = list.indexOfFirst { it.id == task.id }
        if (index != -1) {
            list[index] = list[index].copy(isCompleted = !task.isCompleted)
            tasks.value = list
            repo.saveTasks(list)
        }
    }
}
