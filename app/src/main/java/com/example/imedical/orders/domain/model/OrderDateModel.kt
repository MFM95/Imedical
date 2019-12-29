package com.example.imedical.orders.domain.model

import com.google.gson.annotations.SerializedName

data class OrderDateModel (
    @SerializedName("date") val date: String
    )