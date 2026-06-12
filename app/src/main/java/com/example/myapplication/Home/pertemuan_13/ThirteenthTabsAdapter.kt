package com.example.myapplication.Home.pertemuan_13

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ThirteenthTabsAdapter(
    activity: ThirteenthActivity
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {

        return when(position){

            0 -> TabCaptureFragment()

            1 -> TabScanFragment()

            else -> TabQrcodeFragment()
        }
    }
}