package com.example.imedical.orders.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.orders.domain.interactor.GetOrdersUseCase
import com.example.imedical.orders.domain.model.OrderModel
import com.example.imedical.orders.domain.model.OrdersWrapperModel
import javax.inject.Inject

class OrdersViewModel @Inject constructor(
    private val ordersUseCase: GetOrdersUseCase
):ViewModel(){
    val ordersLiveData by lazy { MutableLiveData<DataWrapper<OrdersWrapperModel>>() }

    fun getOrders(page: Int){
        ordersUseCase.execute(page, this::onOrdersResult)
    }

    private fun onOrdersResult(response: DataWrapper<OrdersWrapperModel>){
        ordersLiveData.value = response
    }
}