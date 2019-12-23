package com.example.imedical.home.presentation.view.fragment


import android.annotation.SuppressLint
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
import com.example.imedical.addresses.presentation.view.activity.ProfileActivity
import com.example.imedical.categories.presentation.view.activity.CategoriesActivity
import com.example.imedical.compare.presentation.view.fragment.CompareListFragment
import com.example.imedical.core.platform.BaseFragment
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.home.presentation.viewmodel.NavigationViewModel
import com.example.imedical.login.domain.model.UserModel
import com.example.imedical.login.presentation.view.activity.LoginActivity
import com.example.imedical.shop.presentation.view.fragment.ShopFragment
import com.example.imedical.wishlist.presentation.view.fragment.WishListFragment
import kotlinx.android.synthetic.main.app_bar_home.*
import javax.inject.Inject


class NavigationFragment : BaseFragment(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navView: NavigationView
    private lateinit var navTitle: TextView

    private lateinit var viewModel: NavigationViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<NavigationViewModel>

    private var userModel: UserModel? = null
    private var selectedFragment: SelectedFragment = SelectedFragment.HOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(NavigationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_navigation, container, false)
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

    private fun subscribeViewModel() {
        val token = userPreferences.getAccessToken()

        if(token != null && token.isNotEmpty())
            viewModel.getUser("Bearer $token").observe(this, Observer { dataWrapper ->
                if(dataWrapper!= null && dataWrapper.status) {
                    navTitle.text = dataWrapper.data?.name
                    this.userModel = dataWrapper.data
                    userPreferences.saveUserObject(this.userModel)
                    showLogout()
                } else {
                    userPreferences.clearUser()
                    userModel = null
                    setTitleAction()
                    navTitle.text = getString(R.string.nav_header_title)
                    hideLogout()
                }
            })
        else hideLogout()
    }

    private fun setTitleAction() {
        navTitle = navView.getHeaderView(0).findViewById(R.id.navDrawerTitle)
        navTitle.setOnClickListener {
            if (userModel == null)
                activity!!.startActivity(Intent(activity, LoginActivity::class.java))
            else {
                startActivity(Intent(activity, ProfileActivity::class.java))
            }
        }
    }

    @SuppressLint("RestrictedApi")
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_login ->{
                activity!!.startActivity(Intent(activity, LoginActivity::class.java))
            }
            R.id.nav_home -> {
                if(fragmentManager != null)
                    activity?.supportFragmentManager?.beginTransaction()!!
                        .replace(R.id.homeFragment, HomeFragment())
                        .commitNow()
            }
//            R.id.nav_categories -> {
//                val intent = Intent(activity, CategoriesActivity::class.java)
//                startActivity(intent)
//            }
            R.id.nav_shop ->{
                replaceFragment(ShopFragment.newInstance())
            }
            R.id.nav_wish_list -> {
                if(fragmentManager != null && userPreferences.isUserLogged())
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.homeFragment, WishListFragment())?.commitNow()
                else {
                    navView.setCheckedItem(R.id.nav_home)
                    showMessage("Login to be able to use this feature")
                }

            }
            R.id.nav_compare_list -> {
                    replaceFragment(CompareListFragment.newInstance())
//                    fab.visibility = View.GONE
//                    toolbar.title = getString(R.string.compare_list_title)
            }
            R.id.nav_settings -> {
                val intent = Intent(activity, ProfileActivity::class.java)
                startActivity(intent)
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

    private fun replaceFragment(fragment: Fragment) {
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.homeFragment, fragment)
        transaction.commit()
    }

    enum class SelectedFragment {
        HOME,
        CATEGORIES,
        SHOP,
        WISHLIST,
        COMPARELIST,
        SETTINGS
    }
    private fun hideLogout(){
        navView.menu.findItem(R.id.nav_settings).isVisible = false
        navView.menu.findItem(R.id.nav_logout).isVisible = false
        navView.menu.findItem(R.id.nav_login).isVisible = true
    }

    private fun showLogout(){
        navView.menu.findItem(R.id.nav_settings).isVisible = true
        navView.menu.findItem(R.id.nav_logout).isVisible = true
        navView.menu.findItem(R.id.nav_login).isVisible = false
    }
}
