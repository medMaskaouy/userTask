package com.example.usertasksmanager.ui.taskDetail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.usertasksmanager.R
import com.example.usertasksmanager.models.Task
import com.example.usertasksmanager.utils.inflate

class TaskAdapter(private val tasks: List<Task>) : RecyclerView.Adapter<TaskViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflatedView = parent.inflate(R.layout.task_item, false)
        return TaskViewHolder(inflatedView)

    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bindView(task)

    }

}
