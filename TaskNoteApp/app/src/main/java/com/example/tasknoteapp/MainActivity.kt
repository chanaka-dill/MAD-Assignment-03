package com.example.tasknoteapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasknoteapp.ui.AddTaskActivity
import com.example.tasknoteapp.ui.TaskAdapter
import com.example.tasknoteapp.viewmodel.TaskViewModel

class MainActivity : AppCompatActivity() {

    private val vm: TaskViewModel by viewModels()
    private lateinit var adapter: TaskAdapter

    private val addTaskLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val title = result.data?.getStringExtra("title") ?: return@registerForActivityResult
                val description = result.data?.getStringExtra("description")
                vm.addTask(title, description)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.recyclerTasks)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        adapter = TaskAdapter(
            items = emptyList(),
            onToggle = { vm.toggleCompleted(it) },
            onDelete = { vm.deleteTask(it) }
        )

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        vm.tasks.observe(this) { list ->
            adapter.update(list)
        }

        btnAdd.setOnClickListener {
            addTaskLauncher.launch(Intent(this, AddTaskActivity::class.java))
        }
    }
}
