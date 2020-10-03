package com.example.usertasksmanager.repositories.taskRepository

import android.content.Context
import com.example.usertasksmanager.api.RetrofitApi

class TaskRepository (var context: Context){

    suspend fun getTaskByUserId(id : Int ) = RetrofitApi.getTasksApi(context).getTaskByUserId(id)
}