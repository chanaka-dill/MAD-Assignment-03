package com.example.tasknoteapp.data

import android.content.Context
import com.example.tasknoteapp.model.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TaskRepository(context: Context) {

    private val prefs = context.getSharedPreferences("task_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val KEY_TASKS = "tasks_json"

    fun saveTasks(tasks: List<Task>) {
        // Secure Coding Practice #1:
        // Store only non-sensitive data locally. Do NOT store passwords/tokens in SharedPreferences.
        val json = gson.toJson(tasks)
        prefs.edit().putString(KEY_TASKS, json).apply()
    }

    fun loadTasks(): MutableList<Task> {
        val json = prefs.getString(KEY_TASKS, null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<Task>>() {}.type
        return gson.fromJson(json, type) ?: mutableListOf()
    }
}