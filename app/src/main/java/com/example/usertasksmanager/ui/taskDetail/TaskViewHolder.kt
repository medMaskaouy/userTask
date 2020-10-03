package com.example.usertasksmanager.ui.taskDetail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.usertasksmanager.models.Task
import kotlinx.android.synthetic.main.task_item.view.*

class TaskViewHolder (private var view : View) : RecyclerView.ViewHolder(view) {

    fun bindView(task: Task) {
        view.tv_title.text = task.title
        Glide.with(view.context)
            .load(getImage(view,task.completed))
            .into(view.iv_status)
    }

    fun getImage(view: View,compledted : Boolean): Int {
        val imageName = if(compledted) "validate" else "close"
        return view.context.resources.getIdentifier(imageName,"drawable",view.context.packageName)
    }
}