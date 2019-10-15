package com.example.imedical.addresses.data.api

import com.example.imedical.addresses.data.entity.*
import com.example.imedical.core.api.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface AddressesAPI {
    @GET("addresses")
    suspend fun getAddresses()
            : Response<ApiResponse<GetAddressesResponse>>

    @POST("addresses/create")
    suspend fun createAddress(@Body createAddressBody: CreateAddressBody)
            : Response<ApiResponse<CreateAddressResponse>>


    @GET("countries-with-provinces")
    suspend fun getCountryProvinces(@Body id: String?)
            : Response<ApiResponse<CountryProvincesResponse>>


    @POST("addresses/delete/")
    suspend fun deleteAddress(@Url id: String)
            : Response<ApiResponse<DeleteAddressResponse>>

    @POST("addresses/update/")
    suspend fun updateAddress(@Url id: String,
                              @Body createAddressBody: CreateAddressBody)
            : Response<ApiResponse<UpdateAddressResponse>>


}