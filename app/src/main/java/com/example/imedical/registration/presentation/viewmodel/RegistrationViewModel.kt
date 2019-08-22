package com.example.imedical.registration.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.registration.domain.usecase.RegisterUseCase
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) : ViewModel() {
    private var tokenLiveData: MutableLiveData<DataWrapper<String>> = MutableLiveData()

    fun getToken(): LiveData<DataWrapper<String>> {
        return tokenLiveData
    }

    fun register(name: String, email: String, mobile: String, pass: String, passConf: String){
        registerUseCase.execute(
            RegisterUseCase.RegistrationParams(
            name = name, email = email, mobile = mobile, password = pass, passwordConf = passConf),
            onResult = this::onResult)

    }

    private fun onResult(result: DataWrapper<String>){
        tokenLiveData.value = result
    }
}
