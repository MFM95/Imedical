package com.example.imedical.orders.data.repository

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.orders.domain.model.OrderModel
import com.example.imedical.orders.domain.model.OrdersWrapperModel

object OrdersMapper {
    fun mapOrdersList(apiResponse: ApiResponse<OrdersWrapperModel>?): DataWrapper<OrdersWrapperModel>{
        return if(apiResponse != null){
            DataWrapper(apiResponse.status?:false, apiResponse.data, apiResponse.error)
        } else DataWrapper(false, null, "Server error")
    }
}