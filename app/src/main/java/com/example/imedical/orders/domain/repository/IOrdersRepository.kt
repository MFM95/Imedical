package com.example.imedical.orders.domain.repository

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.orders.domain.model.OrdersWrapperModel

interface IOrdersRepository {
    suspend fun getOrders(page: Int): DataWrapper<OrdersWrapperModel>
}