package com.willlockwood.lockwoodtakehome.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.willlockwood.lockwoodtakehome.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initNavigationButtons()
    }

    private fun initNavigationButtons() {
        val navController = findNavController()
        pager_btn.setOnClickListener            { navController.navigate(R.id.action_homeFragment_to_pagerFragment) }
        state_pager_btn.setOnClickListener      { navController.navigate(R.id.action_homeFragment_to_statePagerFragment) }
        recycler_btn.setOnClickListener         { navController.navigate(R.id.action_homeFragment_to_recyclerFragment) }
        swipe_recycler_btn.setOnClickListener   { navController.navigate(R.id.action_homeFragment_to_recyclerSwipeFragment) }
    }
}
