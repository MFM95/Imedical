package com.example.imedical.cart.domain.repository

import com.example.imedical.cart.domain.model.CartModel
import com.example.imedical.core.model.DataWrapper

interface ICartRepository {
    suspend fun removeFromCart(productRowId: String): DataWrapper<Unit>
    suspend fun updateCartItem(productRowId: String, quantity: Int): DataWrapper<Unit>
    suspend fun getCart(): DataWrapper<CartModel>
}