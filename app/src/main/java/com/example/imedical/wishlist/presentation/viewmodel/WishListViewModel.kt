package com.example.imedical.wishlist.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.model.ProductModel
import com.example.imedical.wishlist.domain.usecase.RemoveWishUseCase
import com.example.imedical.wishlist.domain.usecase.WishListUseCase
import javax.inject.Inject

class WishListViewModel @Inject constructor(
    private val wishListUseCase: WishListUseCase,
    private val removeWishUseCase: RemoveWishUseCase
)
    : ViewModel() {

    private val wishListLiveData: MutableLiveData<DataWrapper<List<ProductModel>>> = MutableLiveData()

    private val removeWishLiveData: MutableLiveData<DataWrapper<Int>> = MutableLiveData()

    fun getWithListLiveData(): LiveData<DataWrapper<List<ProductModel>>> {
        return wishListLiveData
    }

    fun updateWishList() {
        wishListUseCase.execute(Unit, this::onResult)
    }

    fun getRemoveWishLiveData(): LiveData<DataWrapper<Int>>{
        return removeWishLiveData
    }

    fun removeWish(id: Int, index: Int){
        removeWishUseCase.execute(RemoveWishUseCase.RemoveWishParams(id, index), this::onWishRemoveResult)
    }

    private fun onWishRemoveResult(dataWrapper: DataWrapper<Int>){
        removeWishLiveData.value = dataWrapper
    }

    private fun onResult(dataWrapper: DataWrapper<List<ProductModel>>) {
        wishListLiveData.value = dataWrapper
    }

}
