package com.example.imedical.shop.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.home.data.entity.BrandEntity
import com.example.imedical.shop.domain.interactor.GetBrandsUseCase
import javax.inject.Inject

class FilterViewModel @Inject constructor(
    private val brandsUseCase: GetBrandsUseCase
) : ViewModel() {
    val brandsLiveData by lazy { MutableLiveData<DataWrapper<List<BrandEntity>>>() }

    fun getBrands(){
        brandsUseCase.execute(Unit, this::onBrandsResult)
    }

    private fun onBrandsResult(data: DataWrapper<List<BrandEntity>>){
        brandsLiveData.value = data
    }
}