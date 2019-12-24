package com.example.imedical.cart.data.api

import com.example.imedical.cart.data.entity.CartResponse
import com.example.imedical.core.api.ApiResponse
import com.example.imedical.home.data.api.ResponseError
import retrofit2.Response
import retrofit2.Retrofit
import java.net.UnknownHostException
import javax.inject.Inject

class CartApiCalls@Inject constructor(private val retrofit: Retrofit) {
    private val cartApi = retrofit.create(CartApi::class.java)

    suspend fun removeFromCart(productId: String): ApiResponse<CartResponse>? {
        var data: ApiResponse<CartResponse>? = null
        try {
            val response = cartApi.removeFromCart(productId)
            data = if (response.isSuccessful)
                response.body()
            else ResponseError.handle(response, retrofit)
        } catch (ex: UnknownHostException) {
            data = ApiResponse(false, null, "Check your internet connection")
        } catch (ex: Exception){
            ex.printStackTrace()
        }

        return data
    }


    suspend fun updateCartItem(productId: String, quantity: Int): ApiResponse<CartResponse>? {
        var data: ApiResponse<CartResponse>? = null
        try {
            val response = cartApi.updateCartItem(productId, quantity)
            data = if (response.isSuccessful)
                response.body()
            else ResponseError.handle(response, retrofit)

        } catch (ex: UnknownHostException) {
            data = ApiResponse(false, null, "Check your internet connection")
        }

        return data
    }

    suspend fun getCartItems(): ApiResponse<CartResponse>{
        var data: ApiResponse<CartResponse>? = null
        var response: Response<ApiResponse<CartResponse>>
        try {
            response = cartApi.getCartItems()
            data = if (response.isSuccessful)
                 response.body()
            else ResponseError.handle(response, retrofit)
        } catch (ex: UnknownHostException) {
            data = ApiResponse(false, null, "Check your internet connection")
        } catch (ex: java.lang.Exception){
            data = ApiResponse(false, null, "Server Error")
        }

        return data?: ApiResponse<CartResponse>(false, null, "Server Error")
    }
}