package com.willlockwood.lockwoodtakehome.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.willlockwood.lockwoodtakehome.R
import com.willlockwood.lockwoodtakehome.adapter.LockwoodStatePagerAdapter
import com.willlockwood.lockwoodtakehome.viewmodel.LockwoodVM
import kotlinx.android.synthetic.main.fragment_view_pager.view_pager
import kotlinx.android.synthetic.main.fragment_view_state_pager.*

class StatePagerFragment : Fragment() {

    private val lockwoodVM: LockwoodVM by viewModels()
    private lateinit var viewPager: ViewPager
    private lateinit var lockwoodStatePagerAdapter: LockwoodStatePagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_view_state_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewPager()
    }

    private fun setUpViewPager() {
        viewPager = view_pager
        lockwoodStatePagerAdapter = LockwoodStatePagerAdapter(
            listOf("first", "second", "third", "fourth"),
            childFragmentManager
        )
        viewPager.adapter = lockwoodStatePagerAdapter
        state_pager_tabs.setupWithViewPager(viewPager)
    }
}
