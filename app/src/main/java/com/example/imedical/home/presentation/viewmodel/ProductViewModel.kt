package com.example.imedical.home.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.cart.domain.model.CartModel
import com.example.imedical.compare.domain.interactor.AddToCompareListUseCase
import com.example.imedical.compare.domain.interactor.RemoveFromCompareListUseCase
import com.example.imedical.core.UserPreferences
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.model.ProductModel
import com.example.imedical.home.domain.usecase.AddToCartUseCase
import com.example.imedical.home.domain.usecase.StoreWishUseCase
import com.example.imedical.wishlist.domain.usecase.RemoveWishUseCase
import javax.inject.Inject

class ProductViewModel @Inject constructor(
    private val userPreferences: UserPreferences,
    private val addToCartUseCase: AddToCartUseCase,
    private val storeWishUseCase: StoreWishUseCase,
    private val removeWishUseCase: RemoveWishUseCase,
    private val addToCompareListUseCase: AddToCompareListUseCase) : ViewModel() {

    private val addToCartLiveData by lazy { MutableLiveData<DataWrapper<CartModel>>() }
    private val wishLiveData: MutableLiveData<DataWrapper<Int>> = MutableLiveData()
    private val addLiveData: MutableLiveData<Unit> = MutableLiveData()

    fun getAddToCartLiveData(): LiveData<DataWrapper<CartModel>>{
        return addToCartLiveData
    }

    fun addToCart(productId: Int, quantity: Int){
        addToCartUseCase.execute(AddToCartUseCase.AddCartParams(productId, quantity), this::onAddResult)
    }

    fun getWishLiveData(): LiveData<DataWrapper<Int>>{
        return wishLiveData
    }

    fun getAddCompareLiveData(): LiveData<Unit> {
        return addLiveData
    }

    fun addWish(id: Int, index: Int){
        storeWishUseCase.execute(StoreWishUseCase.AddWishParams(id, index), this::onWishResult)
    }

    fun addToCompareList(product: ProductModel) {
        addToCompareListUseCase.execute(product, this::onAddToCompareListResult)
    }

    private fun onAddToCompareListResult(result: Unit) {
        addLiveData.value = result
    }

    fun removeWish(id: Int, index: Int){
        removeWishUseCase.execute(RemoveWishUseCase.RemoveWishParams(id, index), this::onWishResult)
    }
    private fun onWishResult(dataWrapper: DataWrapper<Int>){
        wishLiveData.value = dataWrapper
    }

    private fun onAddResult(dataWrapper: DataWrapper<CartModel>){
        if(dataWrapper.status)
            userPreferences.setCartSize(dataWrapper.data?.cartItems?.size?:0)
        addToCartLiveData.value = dataWrapper
    }
}