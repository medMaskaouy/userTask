package com.example.usertasksmanager.ui.userslist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.usertasksmanager.models.User
import kotlinx.android.synthetic.main.user_item.view.*

class UserViewHolder (private var view : View) : RecyclerView.ViewHolder(view) {

    fun bindView(user: User, clickListner : onUserClickListner) {

        view.tv_name.text = user.name
        view.tv_username.text = user.username
        view.tv_email.text = user.email

        itemView.setOnClickListener{
            clickListner.onUserClicked(user,getBindingAdapterPosition())
        }
    }

}