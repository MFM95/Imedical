package com.example.imedical.verification.presentation.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.imedical.R
import com.example.imedical.core.platform.BaseFragment
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.registration.presentation.viewmodel.RegistrationViewModel
import com.example.imedical.verification.presentation.viewmodel.VerificationViewModel
import kotlinx.android.synthetic.main.fragment_verification.*
import kotlinx.android.synthetic.main.registration_fragment.*
import javax.inject.Inject

class VerificationFragment : BaseFragment() {

    private lateinit var viewModel: VerificationViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<VerificationViewModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_verification, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(VerificationViewModel::class.java)
        subscribeViewModel()
        setupActions()
    }


    private fun subscribeViewModel(){
        viewModel.getResponseLiveData()
            .observe(
                this, Observer { dataWrapper ->
                    //TODO remove showing token
                    if(dataWrapper?.status == true)
                        showMessage(dataWrapper.data)
                    else{
                        registerErrorLayout.visibility = View.VISIBLE
                        registerErrorTextView.text = dataWrapper?.error
                    }
                }
            )
    }

    private fun setupActions(){
        btnVerify.setOnClickListener {
            onVerifyClickListener()
        }
        tvResendCode.setOnClickListener {
            // todo
        }
    }

    private fun onVerifyClickListener() {
        val code = edtVerificationCode.text.toString()
        if (code.isEmpty()) {
            showMessage(getString(R.string.empty_verification_code))
        } else {
            viewModel.verify(code)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = VerificationFragment()
    }
}
