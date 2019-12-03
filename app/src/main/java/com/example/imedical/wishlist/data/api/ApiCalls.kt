package com.example.imedical.wishlist.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.api.EmptyResponse
import com.example.imedical.core.api.ProductEntity
import com.example.imedical.home.data.api.ResponseError
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
class ApiCalls @Inject constructor(private val retrofit: Retrofit) {
    private val api = retrofit.create(WishApi::class.java)

    suspend fun getWishList() : ApiResponse<List<ProductEntity>>?{
        val response = api.getWishList()

        return if(response.isSuccessful)
            response.body()
        else ResponseError.handle(response, retrofit)
    }

    suspend fun storeWish(id: Int): ApiResponse<Unit>?{
        val response =  api.storeWish(id)
        return if(response.isSuccessful)
            response.body()
        else ResponseError.handle(response, retrofit)
    }


    suspend fun removeWish(id: Int): ApiResponse<EmptyResponse>?{
        return  api.removeWish(id).body()
    }
}