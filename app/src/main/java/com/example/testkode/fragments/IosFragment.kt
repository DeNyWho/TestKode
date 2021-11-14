package com.example.testkode.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testkode.R
import com.example.testkode.adapter.IosAdapter
import com.example.testkode.models.UserList
import com.example.testkode.viewModel.MainViewModel

class IosFragment() : Fragment() {
    lateinit var recyclerView: RecyclerView

    lateinit var viewModel: MainViewModel
    lateinit var recyclerAdapter: IosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_analytics, container, false)

        recyclerView = view.findViewById(R.id.usersList)

        return view
    }
    private fun initRecyclerView(data: UserList){

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            val decoration =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerAdapter = IosAdapter(data)
            adapter = recyclerAdapter
        }
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getUserList().observe(this, {
            if (it != null){
                initRecyclerView(it)
            }else{
//                setContentView(R.layout.error)
                //Toast.makeText(this, "no result found...", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.getUsersData()
    }
}