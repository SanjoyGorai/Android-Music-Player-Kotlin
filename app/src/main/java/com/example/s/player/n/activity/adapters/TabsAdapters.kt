package com.example.s.player.n.activity.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabsAdapters(fm: FragmentManager, lc: androidx.lifecycle.Lifecycle) :
    FragmentStateAdapter(fm, lc) {

    companion object {
        var tabLists: ArrayList<Fragment> = ArrayList()
    }


    override fun getItemCount(): Int = tabLists.size

    override fun createFragment(position: Int): Fragment =
        tabLists[position]
}