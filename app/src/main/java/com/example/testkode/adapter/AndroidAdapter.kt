package com.example.testkode.adapter

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

class AndroidAdapter(private var list: UserList) : RecyclerView.Adapter<AndroidAdapter.MyViewHolder>() {

    var userList: List<User> = list.items.filter { it.department == "android" }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view){

        val department = view.findViewById<TextView>(R.id.department)
        val  avatarUrl = view.findViewById<ImageView>(R.id.avatarUrl)
        val  name = view.findViewById<TextView>(R.id.fullName)
        val  userTag = view.findViewById<TextView>(R.id.userTag)

        fun bind(user: User) {
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