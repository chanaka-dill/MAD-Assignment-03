package com.example.tasknoteapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tasknoteapp.R
import com.example.tasknoteapp.model.Task

class TaskAdapter(
    private var items: List<Task>,
    private val onToggle: (Task) -> Unit,
    private val onDelete: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskVH>() {

    fun update(newItems: List<Task>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskVH(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TaskVH, position: Int) {
        holder.bind(items[position])
    }

    inner class TaskVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val chkDone: CheckBox = itemView.findViewById(R.id.chkDone)
        private val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        private val txtDescription: TextView = itemView.findViewById(R.id.txtDescription)
        private val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)

        fun bind(task: Task) {
            txtTitle.text = task.title

            if (task.description.isNullOrBlank()) {
                txtDescription.visibility = View.GONE
            } else {
                txtDescription.visibility = View.VISIBLE
                txtDescription.text = task.description
            }

            chkDone.setOnCheckedChangeListener(null)
            chkDone.isChecked = task.isCompleted
            chkDone.setOnCheckedChangeListener { _, _ -> onToggle(task) }

            btnDelete.setOnClickListener { onDelete(task) }
        }
    }
}