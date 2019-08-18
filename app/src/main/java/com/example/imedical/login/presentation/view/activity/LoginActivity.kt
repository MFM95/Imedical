package com.example.imedical.login.presentation.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
