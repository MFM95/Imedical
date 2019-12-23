package com.example.imedical.cart.domain.model

data class CartModel (
    val cartItems: List<CartItemModel>,
    val shippingFee: String,
    val subtotal: String,
    val tax: String,
    val total: String
)