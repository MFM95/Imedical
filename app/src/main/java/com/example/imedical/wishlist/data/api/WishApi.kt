package com.example.imedical.wishlist.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.api.EmptyResponse
import com.example.imedical.core.api.ProductEntity
import com.example.imedical.home.data.entity.DealsEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
interface WishApi {
    @GET("wish-list")
    suspend fun getWishList()
            : Response<ApiResponse<List<ProductEntity>>>

    @POST("wish-list/add/{id}")
    suspend fun storeWish(@Path("id") id: Int): Response<ApiResponse<Unit>>

    @POST("wish-list/remove/{id}")
    suspend fun removeWish(@Path("id") id: Int): Response<ApiResponse<EmptyResponse>>

}