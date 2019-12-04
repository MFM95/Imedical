package com.example.imedical.home.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.model.ProductModel
import com.example.imedical.home.domain.usecase.BestSellersUseCase
import com.example.imedical.home.domain.usecase.StoreWishUseCase
import com.example.imedical.wishlist.domain.usecase.RemoveWishUseCase
import javax.inject.Inject

class BestSellersViewModel @Inject constructor(
    private val bestSellersUseCase: BestSellersUseCase,
    private val storeWishUseCase: StoreWishUseCase,
    private val removeWishUseCase: RemoveWishUseCase) : ViewModel() {

    private val bestSellersLiveData: MutableLiveData<DataWrapper<ArrayList<ProductModel>>> = MutableLiveData()
    private val wishLiveData: MutableLiveData<DataWrapper<Int>> = MutableLiveData()

    fun getBestSellers() : LiveData<DataWrapper<ArrayList<ProductModel>>> {
        return bestSellersLiveData
    }

    fun updateBestSellers(){
        bestSellersUseCase.execute(Unit, onResult = this::onResult)
    }

    fun getWishLiveData(): LiveData<DataWrapper<Int>>{
        return wishLiveData
    }

    fun addWish(id: Int, index: Int){
        storeWishUseCase.execute(StoreWishUseCase.AddWishParams(id, index), this::onWishResult)
    }

    fun removeWish(id: Int, index: Int){
        removeWishUseCase.execute(RemoveWishUseCase.RemoveWishParams(id, index), this::onWishResult)
    }

    private fun onResult(dataWrapper: DataWrapper<ArrayList<ProductModel>>){
        bestSellersLiveData.value = dataWrapper
    }

    private fun onWishResult(dataWrapper: DataWrapper<Int>){
        wishLiveData.value = dataWrapper
    }
}
