package com.example.imedical.home.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.home.domain.usecase.AddToCartUseCase
import javax.inject.Inject

class ProductViewModel @Inject constructor(
    private val addToCartUseCase: AddToCartUseCase) : ViewModel() {

    private val addToCartLiveData by lazy { MutableLiveData<DataWrapper<Unit>>() }

    fun getAddToCartLiveData(): LiveData<DataWrapper<Unit>>{
        return addToCartLiveData
    }

    fun addToCart(productId: Int, quantity: Int){
        addToCartUseCase.execute(AddToCartUseCase.AddCartParams(productId, quantity), this::onResult)
    }

    private fun onResult(dataWrapper: DataWrapper<Unit>){
        addToCartLiveData.value = dataWrapper
    }
}