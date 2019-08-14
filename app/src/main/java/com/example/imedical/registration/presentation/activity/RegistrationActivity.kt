package com.example.imedical.registration.presentation.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.imedical.R
import com.example.imedical.registration.presentation.fragment.RegistrationFragment

class RegistrationActivity : AppCompatActivity() {

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
