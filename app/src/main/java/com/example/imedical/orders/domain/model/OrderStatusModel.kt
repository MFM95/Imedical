package com.example.imedical.orders.domain.model

import com.google.gson.annotations.SerializedName

data class OrderStatusModel (
    @SerializedName("name") val name: String,
    @SerializedName("color") val color: String
    )