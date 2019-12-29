package com.example.imedical.home.data.api

import com.example.imedical.cart.data.entity.CartResponse
import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.api.EmptyResponse
import com.example.imedical.core.api.ProductEntity
import com.example.imedical.home.data.entity.*
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
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

    @POST("auth/get-user-by-token")
    suspend fun getAuthUser(): Response<ApiResponse<UserWrapperEntity>>

    @FormUrlEncoded
    @POST("cart/add")
    suspend fun addToCart(@Field("product") productId: Int, @Field("quantity") quantity: Int): Response<ApiResponse<CartResponse>>

    @GET
    suspend fun getAuthUser(@Field("token") token: String): Response<ApiResponse<UserEntity>>

    @POST("wish-list/add/{id}")
    suspend fun storeWish(@Path("id") id: Int): Response<ApiResponse<EmptyResponse>>

    @FormUrlEncoded
    @POST("checkout/store")
    suspend fun checkout(@Field("address_id") addressId: Int): Response<ApiResponse<Unit>>

    @GET("our-vendors")
    suspend fun getVendors(): Response<ApiResponse<VendorsWrapper>>
}