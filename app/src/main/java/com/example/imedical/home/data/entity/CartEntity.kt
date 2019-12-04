package com.example.imedical.home.data.entity

data class CartEntity(
    var subTotal: Double,
    var tax: Double,
    var shippingFee: Double,
    var total: Double,
    var cartItems: List<CartItemEntity>
)