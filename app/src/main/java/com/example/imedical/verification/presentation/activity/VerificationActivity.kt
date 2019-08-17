package com.example.imedical.verification.presentation.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.imedical.R
import com.example.imedical.core.platform.BaseActivity
import com.example.imedical.verification.presentation.fragment.VerificationFragment

class VerificationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, VerificationFragment.newInstance())
                .commitNow()
        }

    }
}
