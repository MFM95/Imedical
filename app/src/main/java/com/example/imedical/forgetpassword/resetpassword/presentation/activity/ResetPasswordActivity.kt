package com.example.imedical.forgetpassword.resetpassword.presentation.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import com.example.imedical.R
import com.example.imedical.core.platform.BaseActivity
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.core.ui.CustomAlertDialog
import com.example.imedical.forgetpassword.resetpassword.presentation.viewmodel.ResetViewModel
import com.example.imedical.login.presentation.view.activity.LoginActivity
import kotlinx.android.synthetic.main.activity_reset_password.*
import javax.inject.Inject

class ResetPasswordActivity : BaseActivity() {

    @Inject
    lateinit var resetViewModelFactory: ViewModelFactory<ResetViewModel>
    lateinit var resetViewModel: ResetViewModel

    private var mobile = ""
    private var code = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        resetViewModel = ViewModelProviders.of(this, resetViewModelFactory).get(ResetViewModel::class.java)
        init()
    }


    private fun init() {
        getData()
        observeClickListeners()
        observeResetViewModel()
    }

    private fun getData() {
        intent?.let {
            mobile = it.getStringExtra(KEY_MOBILE)!!
            code = it.getStringExtra(KEY_CODE)!!
        }
    }

    private fun observeClickListeners() {
        btnResetSubmit.setOnClickListener {
            var password = edtResetNewPassword.text.toString()
            var passwordConfirmation = edtResetNewPasswordConfirmation.text.toString()
            if(password.isEmpty() || passwordConfirmation.isEmpty()) {
                showMessage(getString(R.string.reset_password_empty))
            } else if (password!= passwordConfirmation) {
                showMessage(getString(R.string.reset_password_dont_match))
            } else {
                showLoading(true)
                resetViewModel.reset(mobile, code, password, passwordConfirmation)
            }
        }
    }

    private fun observeResetViewModel() {
        resetViewModel.getResetResponseLiveData().observe(this, Observer { dataWrapper ->
            showLoading(false)
            if(dataWrapper?.status == true) {
                lyResetErrorLayout.visibility = View.GONE
                showLoginPopup()
            }
            else{
                lyResetErrorLayout.visibility = View.VISIBLE
                tvResetErrorText.text = dataWrapper?.error
            }
        })
    }

    private fun showLoginPopup() {

        CustomAlertDialog.show(this,
            getString(R.string.reset_password_successfully),
            getString(R.string.login),
            View.OnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    finishAffinity()
                } else {
                    finish()
                }
            }
        )
    }

    private fun showLoading(show: Boolean) {
        if(show) {
            progressResetPasswordLoading.visibility = View.VISIBLE
            btnResetSubmit.isClickable = false
            lyResetErrorLayout.visibility = View.GONE
        } else {
            progressResetPasswordLoading.visibility = View.INVISIBLE
            btnResetSubmit.isClickable = true
        }
    }

    companion object {
        private const val KEY_MOBILE = "key_mobile"
        private const val KEY_CODE = "key_code"

        @JvmStatic
        fun newInstance(context: Context): Intent
                = Intent(context, ResetPasswordActivity::class.java)

        fun newInstance(context: Context, mobile: String, code: String): Intent {
            val extraInfo = Bundle().apply {
                putString(KEY_MOBILE, mobile)
            }
            return Intent(context, ResetPasswordActivity::class.java).apply {
                putExtras(extraInfo)
            }
        }
    }
}
