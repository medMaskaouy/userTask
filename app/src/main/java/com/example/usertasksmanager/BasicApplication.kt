package com.example.usertasksmanager

import android.app.Application

class BasicApplication : Application() {
    companion object {
        private lateinit    var basicApplication : BasicApplication

        fun getInstance(): BasicApplication {
            return basicApplication
        }
    }
    override fun onCreate() {
        super.onCreate()
        basicApplication = this
    }

}