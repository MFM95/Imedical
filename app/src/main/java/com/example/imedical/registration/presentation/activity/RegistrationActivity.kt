package com.example.imedical.registration.presentation.activity

import android.os.Bundle
import com.example.imedical.R
import com.example.imedical.core.platform.BaseActivity
import com.example.imedical.registration.presentation.fragment.RegistrationFragment

class RegistrationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RegistrationFragment.newInstance())
                .commitNow()
        }
    }

}
