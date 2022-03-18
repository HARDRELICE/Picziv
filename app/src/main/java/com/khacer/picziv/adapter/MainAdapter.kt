package com.khacer.picziv.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class MainAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle,
    private val fragments: ArrayList<Fragment>
) :
    FragmentStateAdapter(fm, lifecycle) {
    fun getItem(position: Int): Fragment = fragments[position]
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}