package com.example.imedical.forgetpassword.verify.presentation.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.example.imedical.R
import com.example.imedical.core.platform.BaseActivity
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.forgetpassword.forget.presentation.activity.ForgetPasswordActivity
import com.example.imedical.forgetpassword.forget.presentation.viewmodel.ForgetPasswordViewModel
import com.example.imedical.forgetpassword.resetpassword.presentation.activity.ResetPasswordActivity
import com.example.imedical.verification.presentation.activity.VerificationActivity
import com.example.imedical.verification.presentation.viewmodel.VerificationViewModel
import kotlinx.android.synthetic.main.activity_forget_password.*
import kotlinx.android.synthetic.main.activity_verify_password.*
import kotlinx.android.synthetic.main.fragment_verification.*
import javax.inject.Inject

class VerifyPasswordActivity : BaseActivity() {

    private lateinit var verificationViewModel: VerificationViewModel
    @Inject
    lateinit var verificationViewModelFactory: ViewModelFactory<VerificationViewModel>

    private lateinit var forgetPasswordViewModel: ForgetPasswordViewModel
    @Inject
    lateinit var forgetPasswordViewModelFactory: ViewModelFactory<ForgetPasswordViewModel>

    private var mobile = ""
    private var code = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setContentView(R.layout.activity_verify_password)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        verificationViewModel = ViewModelProviders.of(this, verificationViewModelFactory).get(VerificationViewModel::class.java)
        forgetPasswordViewModel = ViewModelProviders.of(this, forgetPasswordViewModelFactory).get(ForgetPasswordViewModel::class.java)
        init()
    }

    private fun init() {
        getData()
        observeVerifyClickListener()
        subscribeVerifying()
        observeResendClickListener()
        subscribeResending()
    }

    private fun getData() {
        intent?.let {
            mobile = it.getStringExtra(KEY_MOBILE)!!
        }
    }

    private fun subscribeVerifying(){
        verificationViewModel.getVerifyResponseLiveData()
            .observe(
                this, Observer { dataWrapper ->
                    showLoading(false)
                    if(dataWrapper?.status == true) {
                        showMessage(dataWrapper.data)
                        lyVerifyPasswordErrorLayout.visibility = View.GONE
                        startActivity(ResetPasswordActivity.newInstance(this, mobile, code))

                    } else{
                        lyVerifyPasswordErrorLayout.visibility = View.VISIBLE
                        tvVerifyPasswordText.text = dataWrapper?.error
                    }
                }
            )
    }


    private fun subscribeResending() {
        forgetPasswordViewModel.getForgetResponseLiveData().observe(this, Observer { dataWrapper ->
            showLoading(false)
            if(dataWrapper?.status == true) {
                waitForResending()
                lyVerifyPasswordErrorLayout.visibility = View.GONE
                showMessage(getString(R.string.verification_code_sent))

            }
            else{
                lyVerifyPasswordErrorLayout.visibility = View.VISIBLE
                tvVerifyPasswordText.text = dataWrapper?.error
            }
        })
    }

    private fun waitForResending() {
        tvVerifyPasswordResend.isClickable = false
        var time = 60
        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvVerifyPasswordResend?.let {
                    if (time == 60)
                        it.text = "1:00"
                    else
                        it.text = "0:" + checkDigit(time)
                }
                time--
            }

            override fun onFinish() {
                tvVerifyPasswordResend?.let {
                    it.text = getString(R.string.btn_resend)
                    it.isClickable = true
                }
            }
        }.start()
    }

    fun checkDigit(number: Int): String {
        return if (number <= 9) "0$number" else number.toString()
    }

    private fun observeVerifyClickListener() {
        btnVerifyPasswordSend.setOnClickListener {
            code = edtVerifyPasswordCode.text.toString()
            if (code.isEmpty()) {
                showMessage(getString(R.string.empty_verification_code))
            } else {
                showLoading(true)
                verificationViewModel.verify(code)
            }
        }
    }

    private fun observeResendClickListener() {
        tvVerifyPasswordResend.setOnClickListener {
            showLoading(true)
            forgetPasswordViewModel.forget(mobile)
        }
    }

    private fun showLoading(show: Boolean) {
        if(show) {
            progressVerifyPasswordLoading.visibility = View.VISIBLE
            btnVerifyPasswordSend.isClickable = false
            lyVerifyPasswordErrorLayout.visibility = View.GONE
        } else {
            progressVerifyPasswordLoading.visibility = View.INVISIBLE
            btnVerifyPasswordSend.isClickable = true
        }
    }

    companion object {
        private const val KEY_MOBILE = "key_mobile"

        @JvmStatic
        fun newInstance(context: Context): Intent
                = Intent(context, VerifyPasswordActivity::class.java)

        fun newInstance(context: Context, mobile: String): Intent {
            val extraInfo = Bundle().apply {
                putString(KEY_MOBILE, mobile)
            }
            return Intent(context, VerifyPasswordActivity::class.java).apply {
                putExtras(extraInfo)
            }
        }
    }

}
