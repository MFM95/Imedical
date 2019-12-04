package com.example.imedical.addresses.presentation.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.imedical.R
import com.example.imedical.addresses.presentation.view.fragment.AddressesFragment
import com.example.imedical.core.platform.BaseActivity

class ProfileActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.lyProfileAddressesContainer, AddressesFragment.newInstance())
        transaction.commit()
    }

    private fun getUserInfo() {

    }
}
