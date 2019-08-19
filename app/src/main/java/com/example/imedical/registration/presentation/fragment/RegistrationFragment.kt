package com.example.imedical.registration.presentation.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.example.imedical.R
import com.example.imedical.core.platform.BaseFragment
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.home.presentation.view.activity.HomeActivity
import com.example.imedical.registration.presentation.viewmodel.RegistrationViewModel
import kotlinx.android.synthetic.main.registration_fragment.*
import javax.inject.Inject

class RegistrationFragment : BaseFragment() {

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    private lateinit var viewModel: RegistrationViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<RegistrationViewModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.registration_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RegistrationViewModel::class.java)
        subscribeViewModel()
        setupActions()
    }

    private fun subscribeViewModel(){
        viewModel.getToken()
            .observe(
                this, Observer { dataWrapper ->
                    if(dataWrapper?.status == true){
                        //Save access token and navigate to home without history
                        userPreferences.saveAccessToken(dataWrapper.data!!)
                        val homeIntent = Intent(activity, HomeActivity::class.java)
                        homeIntent.flags = homeIntent.flags or Intent.FLAG_ACTIVITY_NO_HISTORY
                        startActivity(homeIntent)
                    }
                    else{
                        registerErrorLayout.visibility = View.VISIBLE
                        registerErrorTextView.text = dataWrapper?.error
                    }
                }
            )
    }

    private fun setupActions(){
        submitButton.setOnClickListener { onRegisterClick() }

        //Password confirmation done action
        confirmPasswordEditText.setOnEditorActionListener {
                v: TextView, i: Int, e: KeyEvent? ->
            var handled = false
            if (i == EditorInfo.IME_ACTION_DONE) {
                onRegisterClick()
                handled = true
            }
            handled
        }

        //back to login
        haveAccountTextView.setOnClickListener { activity?.onBackPressed() }
    }

    private fun onRegisterClick(){
        viewModel.register(
            nameEditText.text.toString(),
            emailEditText.text.toString(),
            mobileEditText.text.toString(),
            passwordEditText.text.toString(),
            confirmPasswordEditText.text.toString())
    }
}
