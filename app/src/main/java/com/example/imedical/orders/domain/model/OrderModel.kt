package com.example.imedical.orders.domain.model

import com.example.imedical.addresses.domain.model.AddressModel
import com.google.gson.annotations.SerializedName

data class OrderModel (
    @SerializedName("id") val id: Int,
    @SerializedName("payment") val payment: String,
    @SerializedName("total") val total: Double,
    @SerializedName("totalPaid") val totalPaid: Double,
    @SerializedName("tax") val tax: Double,
    @SerializedName("status") val status: OrderStatusModel,
    @SerializedName("created_at") val date: OrderDateModel,
    @SerializedName("address") val address: AddressModel)