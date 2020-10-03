package com.example.usertasksmanager.api

import com.example.usertasksmanager.models.User
import retrofit2.http.GET

interface UserApi {
    @GET("/users")
    suspend fun  getUsers(): List<User>
}