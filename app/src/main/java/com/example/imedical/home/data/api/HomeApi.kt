package com.example.imedical.home.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.home.data.entity.DealsEntity
import com.example.imedical.core.api.ProductEntity
import com.example.imedical.home.data.entity.UserEntity
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
interface HomeApi {
    @GET("hot-deals")
    suspend fun getOffers()
            : Response<ApiResponse<DealsEntity>>

    @GET("best-sellers")
    suspend fun getBestSellers()
            : Response<ApiResponse<List<List<ProductEntity>>>>

    @GET
    suspend fun getAuthUser(@Field("token") token: String): Response<ApiResponse<UserEntity>>
}