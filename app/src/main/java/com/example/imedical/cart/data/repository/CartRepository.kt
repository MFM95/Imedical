package com.example.imedical.cart.data.repository

import com.example.imedical.cart.data.api.CartApiCalls
import com.example.imedical.cart.domain.model.CartModel
import com.example.imedical.cart.domain.repository.ICartRepository
import com.example.imedical.core.data.CommonDataMapper
import com.example.imedical.core.model.DataWrapper
import javax.inject.Inject

class CartRepository @Inject constructor(private val apiCalls: CartApiCalls) : ICartRepository {
    override suspend fun removeFromCart(productRowId: String): DataWrapper<CartModel> {
        return CartDataMapper.mapCartResponse(apiCalls.removeFromCart(productRowId))
    }

    override suspend fun updateCartItem(productRowId: String, quantity: Int): DataWrapper<CartModel> {
        return CartDataMapper.mapCartResponse(apiCalls.updateCartItem(productRowId, quantity))
    }

    override suspend fun getCart(): DataWrapper<CartModel> {
        return CartDataMapper.mapCartResponse(apiCalls.getCartItems())
    }
}