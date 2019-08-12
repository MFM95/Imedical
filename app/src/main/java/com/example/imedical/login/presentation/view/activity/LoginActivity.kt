package com.example.imedical.login.presentation.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.imedical.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //TODO Remove up enable when parent activity is created
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}
