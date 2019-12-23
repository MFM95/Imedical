package com.example.imedical.home.data.entity

data class CartItemEntity(
    var id: Int,
    var qty: Int,
    var rowId: String,
    var name: String,
    var cover: String,
    var price: Double
)