package com.example.testkode.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testkode.R
import com.example.testkode.models.User
import com.example.testkode.models.UserList
import com.squareup.picasso.Picasso

class UsersAdapter(private var list: UserList) : RecyclerView.Adapter<UsersAdapter.MyViewHolder>() {

    var userList = list.items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UsersAdapter.MyViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view){

          val department = view.findViewById<TextView>(R.id.department)
          val  avatarUrl = view.findViewById<ImageView>(R.id.avatarUrl)
          val  name = view.findViewById<TextView>(R.id.fullName)
          val  userTag = view.findViewById<TextView>(R.id.userTag)

        fun bind(user: User){
            name.text = "${user.firstName} ${user.lastName}"
            department.text = user.department
            userTag.text = user.userTag
            Picasso
                .get()
                .load(user.avatarUrl)
                .into(avatarUrl)

        }

    }


}