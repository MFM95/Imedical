package com.example.imedical.home.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.home.domain.usecase.GetUserUseCase
import com.example.imedical.login.domain.model.UserModel
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
class NavigationViewModel @Inject constructor(private val getUserUseCase: GetUserUseCase) : ViewModel(){
    private val userLiveData = MutableLiveData<DataWrapper<UserModel>>()

    fun getUser(token: String?): LiveData<DataWrapper<UserModel>>{
        token?.let {
            getUserUseCase.execute(token, this::onResult)
        }
        return userLiveData
    }

    private fun onResult(dataWrapper: DataWrapper<UserModel>){
        userLiveData.value = dataWrapper
    }
}