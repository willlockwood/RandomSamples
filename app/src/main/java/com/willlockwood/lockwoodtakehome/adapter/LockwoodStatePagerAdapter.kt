package com.willlockwood.lockwoodtakehome.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.willlockwood.lockwoodtakehome.fragment.PageFragment

class LockwoodStatePagerAdapter(
    val items: List<String>,
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return PageFragment.newInstance(position.toString())
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return items[position]
    }

}