package com.example.imedical.verification.presentation.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.imedical.R
import com.example.imedical.core.platform.BaseFragment
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.verification.presentation.viewmodel.VerificationViewModel
import kotlinx.android.synthetic.main.fragment_verification.*
import javax.inject.Inject

class VerificationFragment : BaseFragment() {

    private lateinit var viewModel: VerificationViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<VerificationViewModel>

    private var mobile = ""

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
        getData()
        subscribeVerification()
        subscribeResending()
        setupActions()
    }

    private fun getData() {
        arguments?.let {
            mobile = it.getString(KEY_MOBILE)!!
        }
    }

    private fun subscribeVerification(){
        viewModel.getVerifyResponseLiveData()
            .observe(
                this, Observer { dataWrapper ->
                    //TODO remove showing token
                    if(dataWrapper?.status == true)
                        showMessage(dataWrapper.data)
                    else{
                        lyVerificationError.visibility = View.VISIBLE
                        tvVerificationError.text = dataWrapper?.error
                    }
                }
            )
    }

    private fun subscribeResending(){
        viewModel.getResendResponseLiveData()
            .observe(
                this, Observer { dataWrapper ->
                    //TODO remove showing token
                    if(dataWrapper?.status == true)
                        showMessage(dataWrapper.data)
                    else{
                        lyVerificationError.visibility = View.VISIBLE
                        tvVerificationError.text = dataWrapper?.error
                    }
                }
            )
    }

    private fun setupActions(){
        btnVerify.setOnClickListener {
            onVerifyClickListener()
        }
        tvResendCode.setOnClickListener {
            onResendClickListener()
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

    private fun onResendClickListener() {
        viewModel.resend(mobile)
    }

    companion object {

        private const val KEY_MOBILE = "key_mobile"
        @JvmStatic
        fun newInstance() = VerificationFragment()

        @JvmStatic
        fun newInstance(mobile: String) =  VerificationFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_MOBILE, mobile)
            }
        }
    }
}
