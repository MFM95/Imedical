package com.example.imedical.verification.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.verification.domain.usecase.ResendUseCase
import com.example.imedical.verification.domain.usecase.VerifyUseCase
import javax.inject.Inject

class VerificationViewModel @Inject constructor(private val verificationUseCase: VerifyUseCase,
                                                private val resendUseCase: ResendUseCase): ViewModel() {
    private var verifyResponseLiveData: MutableLiveData<DataWrapper<String>> = MutableLiveData()
    private var resendResponseLiveData: MutableLiveData<DataWrapper<String>> = MutableLiveData()

    fun getVerifyResponseLiveData(): LiveData<DataWrapper<String>> {
        return verifyResponseLiveData
    }

    fun verify(verificationCode: String) {
        verificationUseCase.execute(
            VerifyUseCase.VerificationParams(verificationCode),
            onResult = this::onVerifyResult)
    }

    private fun onVerifyResult(result: DataWrapper<String>){
        verifyResponseLiveData.value = result
    }

    fun getResendResponseLiveData(): LiveData<DataWrapper<String>> {
        return resendResponseLiveData
    }

    fun resend(mobile: String) {
        resendUseCase.execute(
            ResendUseCase.ResendingParams(mobile),
            onResult = this::onResendResult)
    }

    private fun onResendResult(result: DataWrapper<String>){
        resendResponseLiveData.value = result
    }

}