package com.example.imedical.verification.presentation.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.imedical.R
import com.example.imedical.core.platform.BaseFragment
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.home.presentation.view.activity.HomeActivity
import com.example.imedical.verification.presentation.viewmodel.VerificationViewModel
import kotlinx.android.synthetic.main.activity_verify_password.*
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
                    showLoading(false)
                    if(dataWrapper?.status == true) {
                        showMessage(dataWrapper.data)
                        dataWrapper.data
                        //     userPreferences.saveAccessToken(token!!)
                        val homeIntent = Intent(activity!!, HomeActivity::class.java)
                        homeIntent.flags = homeIntent.flags or
                                Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TOP
                        activity!!.startActivity(homeIntent)

                    } else{
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
                    showLoading(false)
                    if(dataWrapper?.status == true)
                        showMessage(getString(R.string.verification_code_sent))
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
            showLoading(true)
            viewModel.verify(code)
        }
    }

    private fun onResendClickListener() {
        viewModel.resend(mobile)
        showLoading(true)
    }

    private fun showLoading(show: Boolean) {
        if(show) {
            progressVerificationLoading.visibility = View.VISIBLE
            btnVerify.isClickable = false
            tvResendCode.isClickable = false
            lyVerificationError.visibility = View.GONE
        } else {
            progressVerifyPasswordLoading.visibility = View.INVISIBLE
            btnVerify.isClickable = true
            tvResendCode.isClickable = true
        }
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
