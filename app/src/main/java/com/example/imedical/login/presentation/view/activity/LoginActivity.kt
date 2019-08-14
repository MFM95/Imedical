package com.example.imedical.login.presentation.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.imedical.R
import com.example.imedical.core.platform.BaseActivity
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.login.presentation.viewmodel.LoginViewModel
import com.example.imedical.registration.presentation.activity.RegistrationActivity
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
        subscribeViewModel()
        setupActions()
    }

    private fun setupActions(){
        loginButton.setOnClickListener {
            onLoginClick()
        }
        newAccountTextView.setOnClickListener {
            val registerIntent: Intent = Intent(this, RegistrationActivity::class.java)
            startActivity(registerIntent)
        }
    }

    private fun subscribeViewModel(){
        viewModel.getToken()
            .observe(
                this, Observer { dataWrapper ->
                    //TODO remove showing token
                    if(dataWrapper?.status == true)
                        showMessage(dataWrapper.data)
                    else{
                        loginErrorLayout.visibility = View.VISIBLE
                        loginErrorTextView.text = dataWrapper?.error
                    }
                }
            )
    }
    private fun onLoginClick(){
        loginErrorLayout.visibility = View.GONE
        viewModel.login(credentialNameEditText.text.toString(), credentialPasswordEditText.text.toString())
    }
}
