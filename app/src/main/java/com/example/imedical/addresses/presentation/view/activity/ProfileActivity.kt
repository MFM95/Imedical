package com.example.imedical.addresses.presentation.view.activity

import android.os.Bundle
import com.example.imedical.R
import com.example.imedical.addresses.presentation.view.fragment.AddressesFragment
import com.example.imedical.core.platform.BaseActivity
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.lyProfileAddressesContainer, AddressesFragment.newInstance())
        transaction.commit()
        getUserInfo()
    }

    private fun getUserInfo() {
        val userModel = userPreferences.getUserObject()
        tvProfileName.text = userModel?.name
        tvProfileEmail.text = userModel?.email
        tvProfilePhone.text = userModel?.mobile
    }
}
