package com.example.imedical.cart.data.entity

import com.google.gson.annotations.SerializedName

data class CartItem(
    val cover: String,
    val id: Int,
    val name: String,
    val price: Int,
    @SerializedName("qty") val quantity: Int,
    val rowId: String
)