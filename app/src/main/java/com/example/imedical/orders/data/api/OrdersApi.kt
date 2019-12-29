package com.example.imedical.orders.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.orders.domain.model.OrderModel
import com.example.imedical.orders.domain.model.OrdersWrapperModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OrdersApi {
    @GET("orders")
    suspend fun getOrders(@Query("page") page: Int): Response<ApiResponse<OrdersWrapperModel>>
}