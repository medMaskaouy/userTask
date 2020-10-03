package com.example.usertasksmanager.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController.OnDestinationChangedListener
import com.example.usertasksmanager.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        OnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.UsersFragment ->title = resources.getString(R.string.first_fragment_label)
                R.id.TasksFragment ->title = resources.getString(R.string.second_fragment_label)

            }
        }
    }

}