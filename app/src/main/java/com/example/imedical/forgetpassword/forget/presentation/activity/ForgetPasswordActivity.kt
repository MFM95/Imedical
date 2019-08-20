package com.example.imedical.forgetpassword.forget.presentation.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.imedical.R
import com.example.imedical.core.platform.BaseActivity
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.forgetpassword.forget.presentation.viewmodel.ForgetPasswordViewModel
import com.example.imedical.forgetpassword.verify.presentation.activity.VerifyPasswordActivity
import com.example.imedical.login.presentation.viewmodel.LoginViewModel
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
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ForgetPasswordViewModel::class.java)
        observeSendClickListener()
        subscribeForgetViewModel()
    }


    private fun observeSendClickListener() {
        btnForgetSend.setOnClickListener {
            mobile = edtForgetMobile.text.toString()
            viewModel.forget(mobile)
        }
    }

    private fun subscribeForgetViewModel() {
        viewModel.getForgetResponseLiveData().observe(this, Observer { dataWrapper ->
            //TODO remove showing token
            if(dataWrapper?.status == true) {
                lyForgetErrorLayout.visibility = View.GONE
                startActivity(VerifyPasswordActivity.newInstance(this))
            }
            else{
                lyForgetErrorLayout.visibility = View.VISIBLE
                tvForgetErrorText.text = dataWrapper?.error
            }
        })
    }


    companion object {
        @JvmStatic
        fun newInstance(context: Context): Intent
                = Intent(context, ForgetPasswordActivity::class.java)
    }
}
