package com.example.imedical.addresses.data.api

import com.example.imedical.addresses.data.entity.CountryProvincesResponse
import com.example.imedical.addresses.data.entity.CreateAddressBody
import com.example.imedical.addresses.data.entity.CreateAddressResponse
import com.example.imedical.addresses.data.entity.GetAddressesResponse
import com.example.imedical.core.api.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

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


}