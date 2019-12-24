package com.example.imedical.home.data.api

import com.example.imedical.cart.data.entity.CartResponse
import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.api.EmptyResponse
import com.example.imedical.home.data.entity.DealsEntity
import com.example.imedical.core.api.ProductEntity
import com.example.imedical.home.data.entity.UserWrapperEntity
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Field
import java.lang.Exception
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
class ApiCalls @Inject constructor(private val retrofit: Retrofit) {
    private val homeApi = retrofit.create(HomeApi::class.java)

    suspend fun getOffers(): ApiResponse<DealsEntity> {
        return try {
            homeApi.getOffers().body()!!
        } catch (ex: UnknownHostException){
            ApiResponse(false, null, "Check Internet Connection")
        } catch (ex: Exception){
            ApiResponse(false, null, "Server Error")
        }
    }

    suspend fun getBestSellers(): ApiResponse<List<List<ProductEntity>>> {
        return try {
            homeApi.getBestSellers().body()!!
        } catch (ex: UnknownHostException){
            ApiResponse(false, null, "Check Internet Connection")
        } catch (ex: Exception){
            ApiResponse(false, null, "Server Error")
        }
    }

    suspend fun getAuthUser(): ApiResponse<UserWrapperEntity> {
        return try {
            homeApi.getAuthUser().body()!!
        } catch (ex: UnknownHostException) {
            ApiResponse(false, null, "Check Internet Connection")
        } catch (ex: Exception) {
            ApiResponse(false, null, "Server Error")
        }
    }

    suspend fun addToCart(productId: Int, quantity: Int): ApiResponse<CartResponse>? {
        var data: ApiResponse<CartResponse>? = null
        try {
            val response = homeApi.addToCart(productId, quantity)
            if (response.isSuccessful)
                data = response.body()
            else ApiResponse(false, Unit, response.message())
        } catch (ex: UnknownHostException) {
            data = ApiResponse(false, null, "Check your internet connection")
        }

        return data
    }
    suspend fun storeWish(id: Int): ApiResponse<EmptyResponse>?{
        return try {
            homeApi.storeWish(id).body()!!
        } catch (ex: UnknownHostException) {
            ApiResponse(false, null, "Check Internet Connection")
        } catch (ex: Exception) {
            ApiResponse(false, null, "Server Error")
        }
    }

    suspend fun checkout(addressId: Int): ApiResponse<Unit>{
        return try {
            homeApi.checkout(addressId).body()!!
        } catch (ex: UnknownHostException) {
            ApiResponse(false, null, "Check Internet Connection")
        } catch (ex: Exception) {
            ApiResponse(false, null, "Server Error")
        }
    }
}