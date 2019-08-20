package com.example.imedical.forgetpassword.forget.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.forgetpassword.forget.domain.usecase.ForgetPasswordUseCase
import javax.inject.Inject

class ForgetPasswordViewModel @Inject constructor(private val forgetPasswordUseCase: ForgetPasswordUseCase)
    : ViewModel() {
    private var forgetResponseLiveData: MutableLiveData<DataWrapper<String>> = MutableLiveData()

    fun getForgetResponseLiveData(): LiveData<DataWrapper<String>> {
        return forgetResponseLiveData
    }

    fun forget(mobile: String) {
        forgetPasswordUseCase.execute(
            ForgetPasswordUseCase.ForgetPasswordParams(mobile),
            onResult = this::onForgetResult)
    }

    private fun onForgetResult(result: DataWrapper<String>){
        forgetResponseLiveData.value = result
    }

}