package com.example.imedical.login.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.login.domain.model.DataWrapper
import com.example.imedical.login.domain.usecase.LoginUseCase
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel(){
    private var tokenLiveData: MutableLiveData<DataWrapper<String>> = MutableLiveData()

    fun login(user: String, password: String): LiveData<DataWrapper<String>>{
        loginUseCase.execute(LoginUseCase.LoginParams(user, password), this::onResult)
        return tokenLiveData
    }

    private fun onResult(result: DataWrapper<String>){
        tokenLiveData.value = result
    }
}