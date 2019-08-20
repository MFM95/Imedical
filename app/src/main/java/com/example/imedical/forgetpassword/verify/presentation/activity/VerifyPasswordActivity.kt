package com.example.imedical.forgetpassword.verify.presentation.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.imedical.R
import com.example.imedical.forgetpassword.forget.presentation.activity.ForgetPasswordActivity

class VerifyPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_password)
    }


    companion object {
        @JvmStatic
        fun newInstance(context: Context): Intent
                = Intent(context, VerifyPasswordActivity::class.java)
    }
}
