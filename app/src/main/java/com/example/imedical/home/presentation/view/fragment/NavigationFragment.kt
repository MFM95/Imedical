package com.example.imedical.home.presentation.view.fragment


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.imedical.R
import com.example.imedical.login.presentation.view.activity.LoginActivity

class NavigationFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navView: NavigationView
    private lateinit var navTitle: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View  = inflater.inflate(R.layout.fragment_navigation, container, false)
        navView = v.findViewById(R.id.navView)
        navView.setNavigationItemSelectedListener(this)
        setTitleAction()
        return v
    }

    private fun setTitleAction(){
        navTitle = navView.getHeaderView(0).findViewById(R.id.navDrawerTitle)
        navTitle.setOnClickListener {
            activity!!.startActivity(Intent(activity, LoginActivity::class.java))
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
            }
            R.id.nav_categories -> {

            }
            R.id.nav_shop -> {

            }
            R.id.nav_wish_list -> {

            }
            R.id.nav_compare_list -> {

            }
            R.id.nav_settings -> {

            }
            R.id.nav_logout -> {

            }
        }
        val drawerLayout: DrawerLayout = activity!!.findViewById(R.id.drawerLayout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


}
