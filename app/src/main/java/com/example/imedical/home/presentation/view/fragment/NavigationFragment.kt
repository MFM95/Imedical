package com.example.imedical.home.presentation.view.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
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
import com.example.imedical.core.platform.BaseFragment
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.home.presentation.viewmodel.NavigationViewModel
import com.example.imedical.login.domain.model.UserModel
import com.example.imedical.login.presentation.view.activity.LoginActivity
import com.example.imedical.wishlist.presentation.view.fragment.WishListFragment
import javax.inject.Inject

class NavigationFragment : BaseFragment(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navView: NavigationView
    private lateinit var navTitle: TextView

    private lateinit var viewModel: NavigationViewModel
    @Inject lateinit var viewModelFactory: ViewModelFactory<NavigationViewModel>

    private var userModel: UserModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NavigationViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View  = inflater.inflate(R.layout.fragment_navigation, container, false)
        navView = v.findViewById(R.id.navView)
        navView.setNavigationItemSelectedListener(this)
        setHomeChecked()
        setTitleAction()
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeViewModel()
    }

    private fun setHomeChecked(){
        navView.setCheckedItem(R.id.nav_home)
        if(fragmentManager != null)
            activity?.supportFragmentManager?.beginTransaction()!!
                .replace(R.id.homeFragment, HomeFragment())
                .commitNow()
    }

    private fun subscribeViewModel(){
        val token = userPreferences.getAccessToken()
        if(token != null && token.isNotEmpty())
            viewModel.getUser("Bearer $token").observe(this, Observer { dataWrapper ->
                if(dataWrapper!= null && dataWrapper.status) {
                    navTitle.text = dataWrapper.data?.name
                    this.userModel = dataWrapper.data
                    showLogout()
                } else {
                    userPreferences.clearUser()
                    userModel = null
                    setTitleAction()
                    navTitle.text = getString(R.string.nav_header_title)
                    hideLogout()
                }
            })
    }

    private fun setTitleAction(){
        navTitle = navView.getHeaderView(0).findViewById(R.id.navDrawerTitle)
        navTitle.setOnClickListener {
            if(userModel == null)
                activity!!.startActivity(Intent(activity, LoginActivity::class.java))
            else {

            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                if(fragmentManager != null)
                    activity?.supportFragmentManager?.beginTransaction()!!
                        .replace(R.id.homeFragment, HomeFragment())
                        .commitNow()
            }
            R.id.nav_categories -> {

            }
            R.id.nav_shop -> {

            }
            R.id.nav_wish_list -> {
                if(fragmentManager != null)
                activity?.supportFragmentManager?.beginTransaction()!!
                    .replace(R.id.homeFragment, WishListFragment())
                    .commitNow()

            }
            R.id.nav_compare_list -> {

            }
            R.id.nav_settings -> {

            }
            R.id.nav_logout -> {
                userPreferences.clearUser()
                val intent = activity?.intent
                activity?.finish()
                startActivity(intent)
            }
        }
        val drawerLayout: DrawerLayout = activity!!.findViewById(R.id.drawerLayout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun hideLogout(){
        navView.menu.findItem(R.id.nav_logout).isVisible = false
    }

    private fun showLogout(){
        navView.menu.findItem(R.id.nav_logout).isVisible = true
    }
}
