package com.example.usertasksmanager.ui.userslist


import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.usertasksmanager.R
import com.example.usertasksmanager.models.User
import com.example.usertasksmanager.utils.inflate

class UsersAdapter(private val users: List<User>,var clickListner: onUserClickListner) : RecyclerView.Adapter<UserViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflatedView = parent.inflate(R.layout.user_item, false)
        return UserViewHolder(inflatedView)

    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bindView(user,clickListner)

    }

}


interface onUserClickListner{
    fun onUserClicked(user: User, position: Int)
}