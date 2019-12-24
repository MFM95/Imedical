package com.example.imedical.cart.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.cart.domain.model.CartModel
import com.example.imedical.cart.domain.usecase.GetCartUseCase
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.cart.domain.usecase.RemoveFromCartUseCase
import com.example.imedical.cart.domain.usecase.UpdateCartUseCase
import com.example.imedical.core.UserPreferences
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val updateCartUseCase: UpdateCartUseCase,
    private val getCartUseCase: GetCartUseCase,
    private val userPreferences: UserPreferences
) : ViewModel(){

    private val removeCartLiveData by lazy { MutableLiveData<DataWrapper<CartModel>>() }
    private val updateCartLiveData by lazy { MutableLiveData<DataWrapper<CartModel>>() }
    private val getCartLiveData by lazy { MutableLiveData<DataWrapper<CartModel>>() }

    fun getRemoveFromCartLiveData(): LiveData<DataWrapper<CartModel>>{
        return removeCartLiveData
    }

    fun getUpdateCartLiveData(): LiveData<DataWrapper<CartModel>>{
        return updateCartLiveData
    }

    fun getCartLiveData(): LiveData<DataWrapper<CartModel>>{
        return getCartLiveData
    }

    fun removeFromCart(rowId: String){
        removeFromCartUseCase.execute(RemoveFromCartUseCase.RemoveCartParams(rowId), this::onRemoveResult)
    }

    fun updateCartItem(rowId: String, quantity: Int){
        updateCartUseCase.execute(UpdateCartUseCase.UpdateCartParams(rowId, quantity), this::onUpdateResult)
    }

    fun updateCart(){
        getCartUseCase.execute(Unit, this::onCartResult)
    }
    private fun onRemoveResult(dataWrapper: DataWrapper<CartModel>){
        if(dataWrapper.status)
            userPreferences.setCartSize(dataWrapper.data?.cartItems?.size?:0)
        removeCartLiveData.value = dataWrapper
    }

    private fun onUpdateResult(dataWrapper: DataWrapper<CartModel>){
        if(dataWrapper.status)
            userPreferences.setCartSize(dataWrapper.data?.cartItems?.size?:0)
        updateCartLiveData.value = dataWrapper
    }

    private fun onCartResult(dataWrapper: DataWrapper<CartModel>){
        if(dataWrapper.status)
            userPreferences.setCartSize(dataWrapper.data?.cartItems?.size?:0)
        getCartLiveData.value = dataWrapper
    }
}