package com.example.imedical.addresses.data.api

import com.example.imedical.addresses.data.entity.*
import com.example.imedical.core.api.ApiResponse
import retrofit2.Response
import retrofit2.http.*

interface AddressesAPI {
    @GET("addresses")
    suspend fun getAddresses()
            : Response<ApiResponse<GetAddressesResponse>>

    @POST("addresses/create")
    suspend fun createAddress(@Body createAddressBody: CreateAddressBody)
            : Response<ApiResponse<CreateAddressResponse>>


    @GET("addresses/countries-with-provinces/{id}")
    suspend fun getCountryProvinces(@Path("id") id: Int?)
            : Response<ApiResponse<CountryProvincesResponse>>


    @POST("addresses/delete/{id}")
    suspend fun deleteAddress(@Path("id") id: Int)
            : Response<ApiResponse<DeleteAddressResponse>>

    @POST("addresses/update/{id}")
    suspend fun updateAddress(@Path("id") id: Int?,
                              @Body createAddressBody: CreateAddressBody)
            : Response<ApiResponse<UpdateAddressResponse>>


}