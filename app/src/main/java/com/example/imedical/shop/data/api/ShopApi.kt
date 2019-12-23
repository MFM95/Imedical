package com.example.imedical.shop.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.api.ProductEntity
import com.example.imedical.home.data.entity.BrandEntity
import com.example.imedical.shop.data.entity.BrandsResponse
import com.example.imedical.shop.data.entity.ShopResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ShopApi {

    @GET("products")
    suspend fun getProducts(
        @Query("min") minPrice: Double?,
        @Query("max") maxPrice: Double?,
        @Query("q") query: String?,
        @Query("brands") brands: String?,
        @Query("order_by") orderBy: String?,
        @Query("asc") asc: Boolean?,
        @Query("category") category: Int?,
        @Query("page") page: Int)
            : Response<ApiResponse<ShopResponse>>

    @GET("brands")
    suspend fun getBrands(): Response<ApiResponse<BrandsResponse>>
}