package com.example.testkode.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testkode.R
import com.example.testkode.adapter.UsersAdapter
import com.example.testkode.models.User


class EveryOneFragment : Fragment() {

    private var listUsers: MutableList<User> = mutableListOf<User>()
    private var adapter: UsersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_every_one, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.recycler_main)

        listUsers = mutableListOf()

        recycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = UsersAdapter(
            requireContext(),
            listUsers
        )
        adapter!!.notifyDataSetChanged()
        recycler.adapter = adapter

        return view
    }

    private fun isNetworkAvailable(): Boolean{
        var something = 0
        fun Context.isNetworkAvailable(): Boolean {
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
            something = if (activeNetworkInfo != null && activeNetworkInfo.isConnected) 1
            else 0
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
        return something == 1
    }

}