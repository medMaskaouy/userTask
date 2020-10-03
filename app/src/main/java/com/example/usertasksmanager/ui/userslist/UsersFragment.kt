package com.example.usertasksmanager.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.usertasksmanager.BasicApplication
import com.example.usertasksmanager.R
import com.example.usertasksmanager.models.User
import com.example.usertasksmanager.ui.main.MainVMFactory
import com.example.usertasksmanager.ui.main.MainViewModel
import com.example.usertasksmanager.ui.userslist.UsersAdapter
import com.example.usertasksmanager.ui.userslist.onUserClickListner
import kotlinx.android.synthetic.main.fragment_users.*


class UsersFragment : Fragment(),onUserClickListner {

    private lateinit var viewModel: MainViewModel
    private var usersAdapter: UsersAdapter
    private  var usersList : List<User> = emptyList()
    private  var columns = 1

    init {
        usersAdapter = UsersAdapter(usersList,this)
    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            columns = 2
        } else {
            columns = 1
        }
        rc_users.apply {
            layoutManager =  GridLayoutManager(requireContext(), columns)
            adapter = usersAdapter
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireParentFragment( ), MainVMFactory((activity?.application as BasicApplication)))
                        .get(MainViewModel::class.java)


    }

    override fun onResume() {
        super.onResume()
        displayUsers()
    }

    private fun displayUsers(){
        viewModel.users.observe(this, Observer { users ->
            usersAdapter = UsersAdapter(users,this)
            rc_users.apply {
                adapter = usersAdapter
            }
            usersAdapter.notifyDataSetChanged()
        })
    }

    override fun onUserClicked(user: User, position: Int) {
        viewModel.setSelectedUser(user)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }


}