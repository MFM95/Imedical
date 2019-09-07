package com.example.imedical.home.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.home.data.entity.DealsEntity
import com.example.imedical.core.api.ProductEntity
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
class ApiCalls @Inject constructor(retrofit: Retrofit) {
    private val homeApi = retrofit.create(HomeApi::class.java)

    suspend fun getOffers() : Response<ApiResponse<DealsEntity>>{
        return homeApi.getOffers()
    }

    suspend fun getBestSellers() : Response<ApiResponse<List<List<ProductEntity>>>>{
        return homeApi.getBestSellers()
    }

    suspend fun getAuthUser(token: String) = homeApi.getAuthUser(token)
}