package com.example.usertasksmanager.repositories.userRepository

import android.content.Context
import com.example.usertasksmanager.api.RetrofitApi

class UserRepository(var context: Context){
    suspend fun getUsers() = RetrofitApi.getUsersApi(context).getUsers()
}