package com.example.testkode.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testkode.MainFragment
import com.example.testkode.fragments.*


class ListAdapter(
    fragmentManager: FragmentManager,
    fragmentActivity: MainFragment
):
    FragmentStateAdapter(fragmentManager, fragmentActivity.lifecycle)
{
    override fun getItemCount(): Int {
        return 13
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> EveryOneFragment()
            1 -> DesignersFragment()
            2 -> AnalyticsFragment()
            3 -> ManagementFragment()
            4 -> IosFragment()
            5 -> AndroidFragment()
            6 -> FrontendFragment()
            7 -> BackendFragment()
            8 -> QAFragment()
            9 -> HRFragment()
            10 -> PRFragment()
            11 -> SupportFragment()
            12 -> Back_OfficeFragment()
            else -> Fragment()
        }
    }
}