package com.example.imedical.verification.presentation.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.imedical.R
import com.example.imedical.core.platform.BaseActivity
import com.example.imedical.verification.presentation.fragment.VerificationFragment

class VerificationActivity : BaseActivity() {

    private var mobile = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)
        getData()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, VerificationFragment.newInstance(mobile))
                .commitNow()
        }

    }

    private fun getData() {
        intent?.let {
            mobile = it.getStringExtra(KEY_MOBILE)!!
        }
    }

    companion object {

        private const val KEY_MOBILE = "key_mobile"
        @JvmStatic
        fun newInstance() = VerificationActivity()

        fun newInstance(context: Context, mobile: String): Intent {
            val extraInfo = Bundle().apply {
                putString(KEY_MOBILE, mobile)
            }
            return Intent(context, VerificationActivity::class.java).apply {
                putExtras(extraInfo)
            }
        }
    }

}
