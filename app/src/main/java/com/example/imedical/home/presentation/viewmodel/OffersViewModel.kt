package com.example.imedical.home.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.home.domain.model.ProductModel
import com.example.imedical.home.domain.usecase.OffersUseCase
import javax.inject.Inject

class OffersViewModel @Inject constructor(private val offersUseCase: OffersUseCase) : ViewModel() {
    private val offersLiveData: MutableLiveData<ArrayList<ProductModel>> = MutableLiveData()

    fun getOffers() : LiveData<ArrayList<ProductModel>>{
        return offersLiveData
    }

    fun updateOffers(){
        offersUseCase.execute(Unit, onResult = this::onResult)
    }

    private fun onResult(dataWrapper: DataWrapper<ArrayList<ProductModel>>){
        offersLiveData.value = dataWrapper.data
    }
}
