package com.example.imedical.orders.domain.model

import com.google.gson.annotations.SerializedName

data class OrdersWrapperModel (
    @SerializedName("orders") val orders: List<OrderModel>,
    @SerializedName("currentPage") val currentPage: Int,
    @SerializedName("lastPage") val lastPage: Int
)