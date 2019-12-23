package com.example.imedical.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.imedical.R
import com.example.imedical.home.presentation.view.activity.HomeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            CoroutineScope(Dispatchers.Main).launch {
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            }
        }
    }
}
