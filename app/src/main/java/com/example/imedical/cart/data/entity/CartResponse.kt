package com.example.imedical.cart.data.entity

data class CartResponse(
    val cartItems: List<CartItem>,
    val shippingFee: String,
    val subtotal: String,
    val tax: String,
    val total: String
)