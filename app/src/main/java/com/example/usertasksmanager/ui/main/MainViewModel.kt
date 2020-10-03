package com.example.usertasksmanager.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.usertasksmanager.BasicApplication
import com.example.usertasksmanager.models.Task
import com.example.usertasksmanager.models.User
import com.example.usertasksmanager.repositories.taskRepository.TaskRepository
import com.example.usertasksmanager.repositories.userRepository.UserRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel(var context: BasicApplication) : ViewModel() {

        val userRepository: UserRepository = UserRepository(context)
        val taskRepository: TaskRepository = TaskRepository(context)
        private   var  selectedUser : User? = null

        fun setSelectedUser(user : User){
            selectedUser = user
        }

        val users : LiveData<List<User>> = liveData(Dispatchers.IO) {
            val users = userRepository.getUsers()
            emit(users)
        }

        fun getTaskByUserId() : LiveData<List<Task>> = liveData(Dispatchers.IO) {
            val tasks = taskRepository.getTaskByUserId(selectedUser?.id!!)
            emit(tasks)
        }


}