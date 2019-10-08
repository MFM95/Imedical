package com.example.imedical.addresses.data.api

import com.example.imedical.addresses.data.entity.CountryProvincesResponse
import com.example.imedical.addresses.data.entity.CreateAddressBody
import com.example.imedical.addresses.data.entity.CreateAddressResponse
import com.example.imedical.addresses.data.entity.GetAddressesResponse
import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.api.ErrorResponse
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.Exception
import javax.inject.Inject

class APICalls @Inject constructor(private val retrofit: Retrofit) {

    private val api = retrofit.create(AddressesAPI::class.java)

    suspend fun getAddresses(): ApiResponse<GetAddressesResponse> {
        try {
            val response = api.getAddresses()

            //If successful return body
            if (response.isSuccessful)
                return response.body()!!

            //convert error body if not successful
            val errorConverter: Converter<ResponseBody, ErrorResponse> =
                retrofit.responseBodyConverter(ErrorResponse::class.java, arrayOf())
            val errorBody = errorConverter.convert(response.errorBody()!!)

            return ApiResponse(false, null, errorBody!!.error.joinToString(". \n"))

        } catch (ex: Exception){
            return ApiResponse(false, null, ex.message.toString())
        }


    }

    suspend fun createAddress(createAddressBody: CreateAddressBody): ApiResponse<CreateAddressResponse> {
        try {
            val response = api.createAddress(createAddressBody)

            //If successful return body
            if (response.isSuccessful)
                return response.body()!!

            //convert error body if not successful
            val errorConverter: Converter<ResponseBody, ErrorResponse> =
                retrofit.responseBodyConverter(ErrorResponse::class.java, arrayOf())
            val errorBody = errorConverter.convert(response.errorBody()!!)

            return ApiResponse(false, null, errorBody!!.error.joinToString(". \n"))

        } catch (ex: Exception){
            return ApiResponse(false, null, ex.message.toString())
        }


    }

    suspend fun getCountryProvinces(countryId: String?): ApiResponse<CountryProvincesResponse> {
        try {
            val response = api.getCountryProvinces(countryId)

            //If successful return body
            if (response.isSuccessful)
                return response.body()!!

            //convert error body if not successful
            val errorConverter: Converter<ResponseBody, ErrorResponse> =
                retrofit.responseBodyConverter(ErrorResponse::class.java, arrayOf())
            val errorBody = errorConverter.convert(response.errorBody()!!)

            return ApiResponse(false, null, errorBody!!.error.joinToString(". \n"))

        } catch (ex: Exception){
            return ApiResponse(false, null, ex.message.toString())
        }


    }

}