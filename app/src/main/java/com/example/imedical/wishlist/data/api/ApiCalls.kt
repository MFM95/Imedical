package com.example.imedical.wishlist.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.api.ProductEntity
import com.example.imedical.home.data.entity.DealsEntity
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
class ApiCalls @Inject constructor(retrofit: Retrofit) {
    private val api = retrofit.create(WishApi::class.java)

    suspend fun getWishList() : Response<ApiResponse<List<ProductEntity>>>{
        return api.getWishList()
    }
}