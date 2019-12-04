package com.example.imedical.cart.data.api

import com.example.imedical.cart.data.entity.CartResponse
import com.example.imedical.core.api.ApiResponse
import retrofit2.Retrofit
import java.net.UnknownHostException
import javax.inject.Inject

class CartApiCalls@Inject constructor(retrofit: Retrofit) {
    private val cartApi = retrofit.create(CartApi::class.java)

    suspend fun removeFromCart(productId: String): ApiResponse<Unit>? {
        var data: ApiResponse<Unit>? = null
        try {
            val response = cartApi.removeFromCart(productId)
            if (response.isSuccessful)
                data = response.body()
            else ApiResponse(false, Unit, response.message())
        } catch (ex: UnknownHostException) {
            data = ApiResponse(false, Unit, "Check your internet connection")
        }

        return data
    }


    suspend fun updateCartItem(productId: String, quantity: Int): ApiResponse<Unit>? {
        var data: ApiResponse<Unit>? = null
        try {
            val response = cartApi.updateCartItem(productId, quantity)
            if (response.isSuccessful)
                data = response.body()
            else ApiResponse(false, Unit, response.message())
        } catch (ex: UnknownHostException) {
            data = ApiResponse(false, Unit, "Check your internet connection")
        }

        return data
    }

    suspend fun getCartItems(): ApiResponse<CartResponse>{
        var data: ApiResponse<CartResponse>? = null
        try {
            val response = cartApi.getCartItems()
            if (response.isSuccessful)
                data = response.body()
            else ApiResponse(false, null, response.message())
        } catch (ex: UnknownHostException) {
            data = ApiResponse(false, null, "Check your internet connection")
        }

        return data!!
    }
}