package com.example.imedical.home.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.home.data.entity.ProductEntity
import com.example.imedical.home.data.entity.UserEntity
import com.example.imedical.login.domain.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
interface HomeApi {
    @GET("products/offers")
    suspend fun getOffers()
            : Response<ApiResponse<List<ProductEntity>>>

    @GET("products/best_sellers")
    suspend fun getBestSellers()
            : Response<ApiResponse<List<ProductEntity>>>

    @GET
    suspend fun getAuthUser(@Field("token") token: String): Response<ApiResponse<UserEntity>>
}