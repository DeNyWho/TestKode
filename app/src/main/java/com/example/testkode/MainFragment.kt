package com.example.testkode

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.testkode.adapter.ListAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        //SearchView
        val searchView = view.findViewById<SearchView>(R.id.searchView)

        // список
        val tabLayout = view.findViewById<TabLayout>(R.id.TabLayout)
        val viewPager = view.findViewById<ViewPager2>(R.id.ViewPager)

        val adapter = ListAdapter((activity as AppCompatActivity).supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            when(position){
                0 -> tab.text = "Все"
                1 -> tab.text = "Designers"
                2 -> tab.text = "Analysts"
                3 -> tab.text = "Managers"
                4 -> tab.text = "IOS"
                5 -> tab.text = "Android"
                6 -> tab.text = "Frontend"
                7 -> tab.text = "Backend"
                8 -> tab.text = "QA"
                9 -> tab.text = "HR"
                10 -> tab.text = "PR"
                11 -> tab.text = "Supports"
                12 -> tab.text = "BackOffice"
            }
        }.attach()


        return view
    }

}