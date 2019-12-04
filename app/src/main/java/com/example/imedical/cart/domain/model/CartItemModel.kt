package com.example.imedical.cart.domain.model

data class CartItemModel (
    val cover: String,
    val id: Int,
    val name: String,
    val price: Int,
    var quantity: Int,
    val rowId: String
)