package com.example.imedical.home.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.home.data.entity.VendorsWrapper
import com.example.imedical.home.domain.usecase.GetVendorsUseCase
import javax.inject.Inject

class VendorsViewModel @Inject constructor(
    private val vendorsUseCase: GetVendorsUseCase
): ViewModel(){

    val getVendorsLiveData by lazy { MutableLiveData<DataWrapper<VendorsWrapper>>()}

    fun getVendors(){
        vendorsUseCase.execute(Unit, this::onVendorsResult)
    }

    private fun onVendorsResult(result: DataWrapper<VendorsWrapper>){
        getVendorsLiveData.value = result
    }
}