package com.example.imedical.forgetpassword.forget.presentation.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.imedical.R
import com.example.imedical.core.platform.BaseActivity
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.forgetpassword.forget.presentation.viewmodel.ForgetPasswordViewModel
import com.example.imedical.forgetpassword.verify.presentation.activity.VerifyPasswordActivity
import kotlinx.android.synthetic.main.activity_forget_password.*
import javax.inject.Inject

class ForgetPasswordActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<ForgetPasswordViewModel>
    lateinit var viewModel: ForgetPasswordViewModel

    private var mobile = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setContentView(R.layout.activity_forget_password)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ForgetPasswordViewModel::class.java)
        observeSendClickListener()
        subscribeForgetViewModel()
    }


    private fun observeSendClickListener() {
        btnForgetSend.setOnClickListener {
            mobile = edtForgetMobile.text.toString()
            showLoading(true)
            viewModel.forget(mobile)
        }
    }

    private fun subscribeForgetViewModel() {
        viewModel.getForgetResponseLiveData().observe(this, Observer { dataWrapper ->
            showLoading(false)
            if(dataWrapper?.status == true) {
                lyForgetErrorLayout.visibility = View.GONE
                startActivity(VerifyPasswordActivity.newInstance(this, mobile))
            }
            else{
                lyForgetErrorLayout.visibility = View.VISIBLE
                tvForgetErrorText.text = dataWrapper?.error
            }
        })
    }

    private fun showLoading(show: Boolean) {
        if(show) {
            progressForgetPasswordLoading.visibility = View.VISIBLE
            btnForgetSend.isClickable = false
            lyForgetErrorLayout.visibility = View.GONE
        } else {
            progressForgetPasswordLoading.visibility = View.INVISIBLE
            btnForgetSend.isClickable = true
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context): Intent
                = Intent(context, ForgetPasswordActivity::class.java)
    }
}
