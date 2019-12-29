package com.example.imedical.orders.data.repository

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.orders.data.api.OrdersApiCalls
import com.example.imedical.orders.domain.model.OrdersWrapperModel
import com.example.imedical.orders.domain.repository.IOrdersRepository
import javax.inject.Inject

class OrdersRepository @Inject constructor(private val apiCalls: OrdersApiCalls): IOrdersRepository {
    override suspend fun getOrders(page: Int): DataWrapper<OrdersWrapperModel>{
        return OrdersMapper.mapOrdersList(apiCalls.getOrders(page))
    }
}