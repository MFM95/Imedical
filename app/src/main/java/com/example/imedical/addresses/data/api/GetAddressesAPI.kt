package com.example.imedical.addresses.data.api

import com.example.imedical.addresses.data.entity.GetAddressesBody
import com.example.imedical.addresses.data.entity.GetAddressesResponse
import com.example.imedical.core.api.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface GetAddressesAPI {
    @GET("addresses")
    suspend fun getAddresses(@Body body: GetAddressesBody)
            : Response<ApiResponse<GetAddressesResponse>>
}