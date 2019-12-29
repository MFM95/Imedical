package com.example.imedical.orders.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.home.data.api.ResponseError
import com.example.imedical.orders.domain.model.OrderModel
import com.example.imedical.orders.domain.model.OrdersWrapperModel
import com.example.imedical.shop.data.entity.ShopResponse
import com.google.gson.Gson
import retrofit2.Retrofit
import java.net.UnknownHostException
import javax.inject.Inject

class OrdersApiCalls @Inject constructor(
    private val retrofit: Retrofit
){
    private val api = retrofit.create(OrdersApi::class.java)

    suspend fun getOrders(page: Int): ApiResponse<OrdersWrapperModel>?{
        var data: ApiResponse<OrdersWrapperModel>? = null
        try {
            val response = api.getOrders(page)
            data = if(response.isSuccessful && response.body() != null)
                response.body()
            else ResponseError.handle(response, retrofit)
        } catch (ex: UnknownHostException) {
            data = ApiResponse(false, null, "Check your internet connection")
        } catch (ex: Exception){
            ex.printStackTrace()
        }
        return data

    }
}