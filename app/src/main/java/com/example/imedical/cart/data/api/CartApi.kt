package com.example.imedical.cart.data.api

import com.example.imedical.cart.data.entity.CartResponse
import com.example.imedical.core.api.ApiResponse
import retrofit2.Response
import retrofit2.http.*

interface CartApi {
    @FormUrlEncoded
    @POST("cart/update/{id}")
    suspend fun updateCartItem(@Path("id") id: String, @Field("quantity") quantity: Int): Response<ApiResponse<CartResponse>>

    @POST("cart/remove/{id}")
    suspend fun removeFromCart(@Path("id") id: String): Response<ApiResponse<CartResponse>>

    @GET("cart")
    suspend fun getCartItems(): Response<ApiResponse<CartResponse>>

}