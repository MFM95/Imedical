package com.example.imedical.verification.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.verification.domain.usecase.VerificationUseCase
import javax.inject.Inject

class VerificationViewModel @Inject constructor(private val verificationUseCase: VerificationUseCase): ViewModel() {
    private var responseLiveData: MutableLiveData<DataWrapper<String>> = MutableLiveData()

    fun getResponseLiveData(): LiveData<DataWrapper<String>> {
        return responseLiveData
    }

    fun verify(verificationCode: String) {
        verificationUseCase.execute(
            VerificationUseCase.VerificationParams(verificationCode),
            onResult = this::onResult)
    }

    private fun onResult(result: DataWrapper<String>){
        responseLiveData.value = result
    }
}