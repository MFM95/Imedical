package com.example.imedical.login.presentation.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.example.imedical.R
import com.example.imedical.core.platform.BaseActivity
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.forgetpassword.forget.presentation.activity.ForgetPasswordActivity
import com.example.imedical.home.presentation.view.activity.HomeActivity
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        subscribeViewModel()
        setupActions()
    }

    private fun setupActions(){
        //Login button
        loginButton.setOnClickListener {
            onLoginClick()
        }

        //Registration link
        newAccountTextView.setOnClickListener {
            val registerIntent: Intent = Intent(this, RegistrationActivity::class.java)
            startActivity(registerIntent)
        }

        //Password done action
        credentialPasswordEditText.setOnEditorActionListener {
                v: TextView, i: Int, e:KeyEvent? ->
            var handled = false
            if (i == EditorInfo.IME_ACTION_DONE) {
                onLoginClick()
                handled = true
            }
            handled
        }

        forgotPassTextView.setOnClickListener {
            startActivity(ForgetPasswordActivity.newInstance(this))
        }
    }

    private fun subscribeViewModel(){
        viewModel.getToken()
            .observe(
                this, Observer { dataWrapper ->
                    if(dataWrapper?.status == true) {
                        onLoginSuccess(dataWrapper.data)
                    }
                    else{
                        onLoginFail(dataWrapper?.error)
                    }
                }
            )
    }

    private fun onLoginSuccess(token: String?){
        //Save access token and navigate to home without history
        userPreferences.saveAccessToken(token!!)
        val homeIntent = Intent(this, HomeActivity::class.java)
        homeIntent.flags = homeIntent.flags or
                Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(homeIntent)
    }

    private fun onLoginFail(error: String?){
        loginErrorLayout.visibility = View.VISIBLE
        loginErrorTextView.text = error
    }

    private fun onLoginClick(){
        loginErrorLayout.visibility = View.GONE
        viewModel.login(credentialNameEditText.text.toString(), credentialPasswordEditText.text.toString())
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
