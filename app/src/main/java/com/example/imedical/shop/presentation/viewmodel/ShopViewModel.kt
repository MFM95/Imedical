package com.example.imedical.shop.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.model.ProductModel
import com.example.imedical.shop.domain.interactor.ShopUseCase
import com.example.imedical.shop.domain.params.ShopParams
import javax.inject.Inject

class ShopViewModel @Inject constructor(
    private val shopUseCase: ShopUseCase
): ViewModel() {
    private var page = 1
    val shopLiveData = MutableLiveData<DataWrapper<List<ProductModel>>>()
    val shopGridArrange = MutableLiveData<Boolean>()
    private lateinit var shopParams: ShopParams
    fun getShopProducts(minPrice: Double? = null,
                                maxPrice: Double? = null,
                                query: String? = null,
                                brands: List<Int>? = null,
                                orderBy: String? = null,
                                asc: Boolean? = null,
                                category: Int? = null){
        page = 1
        shopParams = ShopParams(minPrice, maxPrice, query, brands, orderBy, asc, category, page)
        shopUseCase.execute(shopParams, this::onResult)
    }

    fun getMorePages(){
        if(page == 1)
            return
        shopParams.page = page
        shopUseCase.execute(shopParams, this::onResult)
    }
    private fun onResult(result: DataWrapper<List<ProductModel>>){
        page++
        shopLiveData.value = result
    }
}