package com.example.imedical.cart.domain.repository

import com.example.imedical.cart.domain.model.CartModel
import com.example.imedical.core.model.DataWrapper

interface ICartRepository {
    suspend fun removeFromCart(productRowId: String): DataWrapper<CartModel>
    suspend fun updateCartItem(productRowId: String, quantity: Int): DataWrapper<CartModel>
    suspend fun getCart(): DataWrapper<CartModel>
}