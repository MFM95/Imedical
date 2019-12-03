package com.example.imedical.addresses.presentation.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.imedical.R
import com.example.imedical.addresses.presentation.view.fragment.AddressesFragment

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.lyHomeFragmentContainer, AddressesFragment.newInstance())
        transaction.commit()
    }
}
