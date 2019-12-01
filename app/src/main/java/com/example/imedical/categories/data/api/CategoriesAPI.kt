package com.example.imedical.categories.data.api

import com.example.imedical.categories.data.entity.GetCategoriesResponse
import com.example.imedical.core.api.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface CategoriesAPI {

    @GET("category")
    suspend fun getAllCategories()
            : Response<ApiResponse<GetCategoriesResponse>>

    @GET("category/get-childs/{id}")
    suspend fun getCategoryChildren(@Path("id") id: Int)
            : Response<ApiResponse<GetCategoriesResponse>>

}