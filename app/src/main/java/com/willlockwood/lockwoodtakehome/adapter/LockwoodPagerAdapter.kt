package com.willlockwood.lockwoodtakehome.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.willlockwood.lockwoodtakehome.fragment.PageFragment

class LockwoodPagerAdapter(
    fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val COUNT = 2

    override fun getItem(position: Int): Fragment {
        return PageFragment.newInstance(position.toString())
    }

    override fun getCount(): Int {
        return COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "first"
            1 -> "second"
            else -> ""
        }
    }

}