package com.example.testkode.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testkode.DetailActivity
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
        holder.itemView.setOnClickListener {
            val something = userList[position]

            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.avatarUrl, something.avatarUrl)
            intent.putExtra(DetailActivity.firstName, something.firstName)
            intent.putExtra(DetailActivity.lastName, something.lastName)
            intent.putExtra(DetailActivity.birthdayl, something.birthday)
            intent.putExtra(DetailActivity.departmentl, something.department)
            intent.putExtra(DetailActivity.phoneNum, something.phone)
            intent.putExtra(DetailActivity.userTagl, something.userTag)
            intent.putExtra(DetailActivity.position, something.position)
            context.startActivity(intent)
        }
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