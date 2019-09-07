package com.example.imedical.wishlist.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.model.ProductModel
import com.example.imedical.wishlist.domain.usecase.WishListUseCase
import javax.inject.Inject

class WishListViewModel @Inject constructor(private val wishListUseCase: WishListUseCase) :
    ViewModel() {
    private val wishListLiveData: MutableLiveData<List<ProductModel>> = MutableLiveData()

    fun getWithListLiveData(): LiveData<List<ProductModel>> {
        return wishListLiveData
    }

    fun updateWishList() {
        wishListUseCase.execute(Unit, this::onResult)
    }

    private fun onResult(dataWrapper: DataWrapper<List<ProductModel>>) {
        wishListLiveData.value = dataWrapper.data
    }
}
