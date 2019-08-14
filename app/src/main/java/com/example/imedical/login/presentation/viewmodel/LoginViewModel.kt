package com.example.imedical.login.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.login.domain.usecase.LoginUseCase
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel(){
    private var tokenLiveData: MutableLiveData<DataWrapper<String>> = MutableLiveData()

    fun getToken(): LiveData<DataWrapper<String>>{
        return tokenLiveData
    }
    fun login(user: String, password: String){
        loginUseCase.execute(LoginUseCase.LoginParams(user, password), this::onResult)
    }

    private fun onResult(result: DataWrapper<String>){
        tokenLiveData.value = result
    }
}