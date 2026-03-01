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

//This TaskAdapter connects the task data with the RecyclerView UI
class TaskAdapter(
    private var items: List<Task>,
    private val onToggle: (Task) -> Unit,
    private val onDelete: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskVH>() {

    //Updates the list when data changes
    fun update(newItems: List<Task>) {
        items = newItems
        notifyDataSetChanged()
    }

    // Creates a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskVH(view)
    }

    // Returns total number of items
    override fun getItemCount(): Int = items.size

    // Binds data to the ViewHolder
    override fun onBindViewHolder(holder: TaskVH, position: Int) {
        holder.bind(items[position])
    }


    inner class TaskVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val chkDone: CheckBox = itemView.findViewById(R.id.chkDone)
        private val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        private val txtDescription: TextView = itemView.findViewById(R.id.txtDescription)
        private val txtStatus: TextView = itemView.findViewById(R.id.txtStatus)
        private val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)


        fun bind(task: Task) {
            txtTitle.text = task.title

            if (task.description.isNullOrBlank()) {
                txtDescription.visibility = View.GONE
            } else {
                txtDescription.visibility = View.VISIBLE
                txtDescription.text = task.description
            }

            //Update Status of the task
            if (task.isCompleted) {
                txtStatus.text = "DONE"
                txtStatus.setBackgroundResource(R.drawable.bg_chip_done)
            } else {
                txtStatus.text = "TODO"
                txtStatus.setBackgroundResource(R.drawable.bg_chip_todo)
            }

            chkDone.setOnCheckedChangeListener(null)
            chkDone.isChecked = task.isCompleted
            chkDone.setOnCheckedChangeListener { _, _ -> onToggle(task) }

            //Delete task
            btnDelete.setOnClickListener { onDelete(task) }
        }
    }
}