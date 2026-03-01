package com.example.tasknoteapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasknoteapp.ui.AddTaskActivity
import com.example.tasknoteapp.ui.TaskAdapter
import com.example.tasknoteapp.viewmodel.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.view.View
import android.widget.LinearLayout

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
        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)
        val emptyState = findViewById<LinearLayout>(R.id.emptyState)

        adapter = TaskAdapter(
            items = emptyList(),
            onToggle = { vm.toggleCompleted(it) },
            onDelete = { vm.deleteTask(it) }
        )

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        vm.tasks.observe(this) { list ->
            adapter.update(list)

            // ✅ show empty state when there are no tasks
            if (list.isEmpty()) {
                emptyState.visibility = View.VISIBLE
                recycler.visibility = View.GONE
            } else {
                emptyState.visibility = View.GONE
                recycler.visibility = View.VISIBLE
            }
        }

        fabAdd.setOnClickListener {
            addTaskLauncher.launch(Intent(this, AddTaskActivity::class.java))
        }
    }
}