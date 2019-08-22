package com.example.imedical.forgetpassword.resetpassword.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.forgetpassword.resetpassword.domain.usecase.ResetUseCase
import javax.inject.Inject

class ResetViewModel @Inject constructor(private val resetUseCase: ResetUseCase)
    : ViewModel() {
    private var resetResponseLiveData: MutableLiveData<DataWrapper<String>> = MutableLiveData()

    fun getResetResponseLiveData(): LiveData<DataWrapper<String>> {
        return resetResponseLiveData
    }

    fun reset(mobile: String, code: String, password: String, passwordConfirmation: String) {
        resetUseCase.execute(
            ResetUseCase.ResetParams(mobile, code, password, passwordConfirmation),
            onResult = this::onResetResult)
    }

    private fun onResetResult(result: DataWrapper<String>){
        resetResponseLiveData.value = result
    }
}