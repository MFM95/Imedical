package com.example.imedical.cart.presentation.view.activity

import android.os.Bundle
import com.example.imedical.R
import com.example.imedical.cart.presentation.view.fragment.CartFragment
import com.example.imedical.core.platform.BaseActivity

class CartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        title = "Cart"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.cartFrameLayout, CartFragment())
            ?.commitNow()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
