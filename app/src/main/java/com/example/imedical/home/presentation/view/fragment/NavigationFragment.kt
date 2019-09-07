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
        //TODO uncomment subscribeViewModel call when GetUser endpoint is ready
        //subscribeViewModel()
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
        if(token != null)
            viewModel.getUser(token).observe(this, Observer { dataWrapper ->
                if(dataWrapper!!.status) {
                    navTitle.text = dataWrapper.data?.name
                    this.userModel = dataWrapper.data
                }
            })
    }

    private fun setTitleAction(){
        navTitle = navView.getHeaderView(0).findViewById(R.id.navDrawerTitle)
        navTitle.setOnClickListener {
            if(userModel == null)
                activity!!.startActivity(Intent(activity, LoginActivity::class.java))
            //TODO put else to open profile of the user
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

            }
        }
        val drawerLayout: DrawerLayout = activity!!.findViewById(R.id.drawerLayout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


}
