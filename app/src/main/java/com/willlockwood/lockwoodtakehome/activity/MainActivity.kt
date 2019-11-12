package com.willlockwood.lockwoodtakehome.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.willlockwood.lockwoodtakehome.R
import com.willlockwood.lockwoodtakehome.viewmodel.LockwoodVM
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val lockwoodVM: LockwoodVM by viewModels()
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavController()     // touches nothing

        initBottomNavBar()      // touches navController

        initNavDrawer()         // touches navController, bottomNavDrawer

        initToolbar()           // touches navDrawer

        initSharedPreferences()
    }

    private fun initNavController() {
        navController = (nav_host_fragment as NavHostFragment).navController
    }

    private fun initBottomNavBar() {
        bottom_nav_bar.setOnNavigationItemSelectedListener(bottomNavItemSelectedListener)
    }

    private fun initNavDrawer() {
        NavigationUI.setupWithNavController(nav_drawer, navController)
        nav_drawer.setNavigationItemSelectedListener(leftNavItemSelectedListener)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_home_black_24dp)
        toolbar.setNavigationOnClickListener {
            drawer_layout.openDrawer(nav_drawer)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                navController.navigate(R.id.action_global_settings)
            }
        }
        return true
    }

    private fun initSharedPreferences() {
        sharedPreferences = getSharedPreferences("com.willlockwood.takehome_preferences", Context.MODE_PRIVATE)
        sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferencesChangeListener)
    }

    private val onSharedPreferencesChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { sharedPrefs, key -> }



    private val bottomNavItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_nav_home -> navController.navigate(R.id.homeFragment)
                R.id.bottom_nav_first -> navController.navigate(R.id.pagerFragment)
                R.id.bottom_nav_second -> navController.navigate(R.id.statePagerFragment)
                R.id.bottom_nav_third -> navController.navigate(R.id.recyclerFragment)
                R.id.bottom_nav_fourth -> navController.navigate(R.id.recyclerSwipeFragment)
            }
            true
        }

    private val leftNavItemSelectedListener =
        NavigationView.OnNavigationItemSelectedListener { item ->
            item.isChecked = true
            drawer_layout.closeDrawer(nav_drawer)
            when (item.itemId) {
                R.id.nav_menu_home -> navController.navigate(R.id.homeFragment)
                R.id.nav_menu_first -> navController.navigate(R.id.pagerFragment)
                R.id.nav_menu_tags -> navController.navigate(R.id.statePagerFragment)
                R.id.nav_menu_privacy -> navController.navigate(R.id.recyclerFragment)
                R.id.nav_menu_terms -> navController.navigate(R.id.recyclerSwipeFragment)
            }
            // When navigating in the left drawer, make the change in the bottom bar
            when (item.itemId) {
                R.id.nav_menu_home -> setBottomNavSelection(R.id.bottom_nav_home)
            }

            true
        }

    private fun setBottomNavSelection(id: Int) {
        val item = bottom_nav_bar.menu.findItem(id)
        item.isChecked = true
    }

    fun toggleNavBarVisibility() {
        when (bottom_nav_bar.isVisible) {
            true -> bottom_nav_bar.visibility = View.GONE
            false -> bottom_nav_bar.visibility = View.VISIBLE
        }
    }

}
