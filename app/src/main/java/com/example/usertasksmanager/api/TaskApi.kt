package com.example.usertasksmanager.api

import com.example.usertasksmanager.models.Task
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TaskApi {
    @GET("todos")
    suspend fun getTaskByUserId(@Query("userId") userId : Int ): List<Task>
}
