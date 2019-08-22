package com.example.imedical.home.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.home.domain.model.ProductModel
import com.example.imedical.home.domain.usecase.BestSellersUseCase
import javax.inject.Inject

class BestSellersViewModel @Inject constructor(private val bestSellersUseCase: BestSellersUseCase) : ViewModel() {
    private val bestSellersLiveData: MutableLiveData<ArrayList<ProductModel>> = MutableLiveData()

    fun getBestSellers() : LiveData<ArrayList<ProductModel>> {
        return bestSellersLiveData
    }

    fun updateBestSellers(){
        bestSellersUseCase.execute(Unit, onResult = this::onResult)
    }

    private fun onResult(dataWrapper: DataWrapper<ArrayList<ProductModel>>){
        bestSellersLiveData.value = dataWrapper.data
    }
}
