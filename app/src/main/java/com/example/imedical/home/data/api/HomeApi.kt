package com.example.imedical.home.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.home.data.entity.ProductEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
interface HomeApi {
    @POST("products/offers")
    suspend fun getOffers()
            : Response<ApiResponse<List<ProductEntity>>>

    @POST("products/best_sellers")
    suspend fun getBestSellers()
            : Response<ApiResponse<List<ProductEntity>>>
}