package com.example.imedical.shop.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.api.ProductEntity
import com.example.imedical.home.data.api.ResponseError
import com.example.imedical.home.data.entity.BrandEntity
import com.example.imedical.shop.data.entity.BrandsResponse
import com.example.imedical.shop.data.entity.ShopResponse
import com.google.gson.Gson
import retrofit2.Response
import retrofit2.Retrofit
import java.net.UnknownHostException
import java.util.*
import javax.inject.Inject

class ShopApiCalls @Inject constructor(private val retrofit: Retrofit) {
    private val api = retrofit.create(ShopApi::class.java)

    suspend fun getProducts(
        minPrice: Double?,
        maxPrice: Double?,
        query: String?,
        brands: List<Int>?,
        orderBy: String?,
        asc: Boolean?,
        category: Int?,
        page: Int)
            : ApiResponse<ShopResponse>?{
        var data: ApiResponse<ShopResponse>? = null
        try {
            val brandsSrc = if(brands == null) null else Gson().toJson(brands)
            val response = api.getProducts(minPrice, maxPrice, query, brands, orderBy, asc, category, page)
            data = if(response.isSuccessful && response.body() != null)
                response.body()
            else ResponseError.handle(response, retrofit)
        } catch (ex: UnknownHostException) {
            data = ApiResponse(false, null, "Check your internet connection")
        } catch (ex: Exception){
            ex.printStackTrace()
        }
        return data
    }

    suspend fun getBrands(): ApiResponse<BrandsResponse>?{
        var data: ApiResponse<BrandsResponse>? = null
        try {
            val response = api.getBrands()
            data = if(response.isSuccessful && response.body() != null)
                response.body()
            else ResponseError.handle(response, retrofit)
        } catch (ex: UnknownHostException) {
            data = ApiResponse(false, null, "Check your internet connection")
        } catch (ex: Exception){
            ex.printStackTrace()
        }
        return data
    }
}