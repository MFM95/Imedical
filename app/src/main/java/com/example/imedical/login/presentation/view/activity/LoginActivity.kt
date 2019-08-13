package com.example.imedical.login.presentation.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.example.imedical.R
import com.example.imedical.core.platform.BaseActivity
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.login.presentation.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory<LoginViewModel>
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        loginButton.setOnClickListener {
            onLoginClick()
        }
    }

    private fun onLoginClick(){
        viewModel.login(credentialNameEditText.text.toString(), credentialPasswordEditText.text.toString())
            .observe(
            this, Observer { dataWrapper ->
                //TODO remove showing token
                if(dataWrapper?.status == true)
                    showMessage(dataWrapper.data)
            }
        )
    }
}
