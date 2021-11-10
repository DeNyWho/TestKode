package com.example.testkode.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testkode.R
import com.example.testkode.response.User
import com.squareup.picasso.Picasso

class UsersAdapter(private val context: Context, private var list: MutableList<User>) : RecyclerView.Adapter<UsersAdapter.MyViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = list[position]
        holder.name?.text = "${user.firstName} ${user.lastName}"
        holder.department?.text = user.department
        Picasso
            .get()
            .load(user.avatarUrl)
            .into(holder.avatarUrl)
    }

    class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view){

        var name: TextView? = null
        var userTag: TextView? = null
        var avatarUrl: ImageView? = null
        var department: TextView? = null

        init {
            department = view.findViewById(R.id.department)
            avatarUrl = view.findViewById(R.id.avatarUrl)
            name = view.findViewById(R.id.fullName)
            userTag = view.findViewById(R.id.userTag)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.user_row,parent,false)
        return MyViewHolder(view)
    }

}