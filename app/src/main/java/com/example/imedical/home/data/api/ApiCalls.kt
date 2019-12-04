package com.example.imedical.home.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.api.EmptyResponse
import com.example.imedical.home.data.entity.DealsEntity
import com.example.imedical.core.api.ProductEntity
import retrofit2.Response
import retrofit2.Retrofit
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
class ApiCalls @Inject constructor(private val retrofit: Retrofit) {
    private val homeApi = retrofit.create(HomeApi::class.java)

    suspend fun getOffers(): Response<ApiResponse<DealsEntity>> {
        return homeApi.getOffers()
    }

    suspend fun getBestSellers(): Response<ApiResponse<List<List<ProductEntity>>>> {
        return homeApi.getBestSellers()
    }

    suspend fun getAuthUser() = homeApi.getAuthUser()

    suspend fun addToCart(productId: Int, quantity: Int): ApiResponse<Unit>? {
        var data: ApiResponse<Unit>? = null
        try {
            val response = homeApi.addToCart(productId, quantity)
            if (response.isSuccessful)
                data = response.body()
            else ApiResponse(false, Unit, response.message())
        } catch (ex: UnknownHostException) {
            data = ApiResponse(false, Unit, "Check your internet connection")
        }

        return data
    }
    suspend fun storeWish(id: Int): ApiResponse<EmptyResponse>?{
        return  homeApi.storeWish(id).body()
    }
}