package com.example.imedical.home.presentation.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.TextView
import com.example.imedical.R
import com.example.imedical.cart.presentation.view.activity.CartActivity
import com.example.imedical.cart.presentation.view.fragment.CartFragment
import com.example.imedical.cart.presentation.viewmodel.CartViewModel
import com.example.imedical.core.platform.BaseActivity
import com.example.imedical.core.platform.ViewModelFactory
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    private var cartMenuItem: MenuItem? = null

    @Inject
    lateinit var cartViewModelFactory: ViewModelFactory<CartViewModel>
    private val cartViewModel by lazy {
        ViewModelProviders.of(this, cartViewModelFactory).get(CartViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setContentView(R.layout.activity_home)
        observeCart()
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun observeCart() {
        cartViewModel.getCartLiveData().observe(this, Observer {
            hideProgress()
            it?.let {
                if (it.status) {
                    if (it.data != null)
                        userPreferences.updateCartSize(it.data.cartItems.size)
                } else showMessage(it.error)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        cartViewModel.updateCart()
        updateCartLabel()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        cartMenuItem = menu.findItem(R.id.action_cart)
        updateCartLabel()
        return true
    }

    fun updateCartLabel() {
        if (cartMenuItem == null)
            return
        val size = userPreferences.getCartSize()
        if (size > 0) {
            cartMenuItem?.actionView?.findViewById<TextView>(R.id.cartSizeTextView)?.text =
                size.toString()
            cartMenuItem?.actionView?.findViewById<TextView>(R.id.cartSizeTextView)?.visibility =
                View.VISIBLE
        } else cartMenuItem?.actionView?.findViewById<TextView>(R.id.cartSizeTextView)?.visibility =
            View.INVISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_cart -> {
                if (userPreferences.isUserLogged())
                    startActivity(Intent(this, CartActivity::class.java))
                else showMessage("Login to be able to use this feature")
            }
        }
        return true
    }
}
