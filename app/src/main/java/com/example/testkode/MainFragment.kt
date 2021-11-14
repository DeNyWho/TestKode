package com.example.testkode

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.testkode.adapter.ListAdapter
import com.example.testkode.models.UserList
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment() : Fragment() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        //SearchView
//        val searchView = view.findViewById<SearchView>(R.id.search_view)
        val filter = view.findViewById<ImageView>(R.id.filter)
        filter.setOnClickListener {
            showDialog()
        }


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

    private fun showDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.fragment_bottom_dialog_sheet)

        dialog.show()
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
        dialog.window!!.setGravity(Gravity.BOTTOM)
    }

}