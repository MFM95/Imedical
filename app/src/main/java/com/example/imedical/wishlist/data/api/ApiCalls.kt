package com.example.imedical.wishlist.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.api.EmptyResponse
import com.example.imedical.core.api.ProductEntity
import com.example.imedical.home.data.api.ResponseError
import retrofit2.Retrofit
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
class ApiCalls @Inject constructor(private val retrofit: Retrofit) {
    private val api = retrofit.create(WishApi::class.java)

    suspend fun getWishList() : ApiResponse<List<ProductEntity>>?{

        return try {
            val response = api.getWishList()
            if (response.isSuccessful)
                response.body()
            else ResponseError.handle(response, retrofit)
        } catch (ex: UnknownHostException){
            ApiResponse(false, null, "Check internet connection")
        }
    }

    suspend fun storeWish(id: Int): ApiResponse<Unit>?{
        return try{
            val response =  api.storeWish(id)
            if(response.isSuccessful)
                response.body()
            else ResponseError.handle(response, retrofit)
        } catch (ex: UnknownHostException){
            ApiResponse(false, null, "Check internet connection")
        }
    }


    suspend fun removeWish(id: Int): ApiResponse<EmptyResponse>?{
        return try {
            api.removeWish(id).body()
        } catch (ex: UnknownHostException){
            ApiResponse(false, null, "Check internet connection")
        }
    }
}