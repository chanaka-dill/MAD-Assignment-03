package com.example.tasknoteapp.ui

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tasknoteapp.R

class AddTaskActivity : AppCompatActivity() {

    private lateinit var edtTitle: EditText
    private lateinit var edtDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        edtTitle = findViewById(R.id.edtTitle)
        edtDescription = findViewById(R.id.edtDescription)
        val btnSave = findViewById<Button>(R.id.btnSave)

        // Preserve UI state on rotation (draft inputs)
        savedInstanceState?.let {
            edtTitle.setText(it.getString("draft_title", ""))
            edtDescription.setText(it.getString("draft_description", ""))
        }

        btnSave.setOnClickListener {
            val title = edtTitle.text.toString()
            val description = edtDescription.text.toString()

            // Validate title cannot be empty.
            if (title.isBlank()) {
                Toast.makeText(this, "Task title is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val data = intent
            data.putExtra("title", title.trim())
            data.putExtra("description", description.trim())

            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }

    // Save current input values before rotation
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("draft_title", edtTitle.text.toString())
        outState.putString("draft_description", edtDescription.text.toString())
    }
}