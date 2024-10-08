package com.example.habitnote.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    arrayList: ArrayList<Fragment>,
    fm:FragmentManager,
    lifecycle: Lifecycle)
    :FragmentStateAdapter(fm,lifecycle) {

        private val fragmentList=arrayList

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}