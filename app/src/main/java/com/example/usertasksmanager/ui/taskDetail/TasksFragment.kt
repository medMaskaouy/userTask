package com.example.usertasksmanager

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.usertasksmanager.ui.main.MainVMFactory
import com.example.usertasksmanager.ui.main.MainViewModel
import com.example.usertasksmanager.ui.taskDetail.TaskAdapter
import kotlinx.android.synthetic.main.fragment_tasks.*


class TasksFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var taskAdapter: TaskAdapter
    private var columns = 1

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            columns = 3
        } else {
            columns = 1
        }

        rc_tasks.apply {
            layoutManager =   GridLayoutManager(requireContext(), columns)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireParentFragment(), MainVMFactory((activity?.application as BasicApplication)))
            .get(MainViewModel::class.java)

        viewModel.getOnError().observe(this, Observer { description  ->
            Toast.makeText(activity,"No data found, please connect to internet", Toast.LENGTH_LONG).show()
        })
    }

    override fun onResume() {
        super.onResume()
        displayTask()
    }
    private fun displayTask(){
        viewModel.getTaskByUserId().observe(this, Observer { tasks ->
            taskAdapter = TaskAdapter(tasks)
            rc_tasks.apply {
                adapter = taskAdapter
            }
            taskAdapter.notifyDataSetChanged()

        })
    }
}