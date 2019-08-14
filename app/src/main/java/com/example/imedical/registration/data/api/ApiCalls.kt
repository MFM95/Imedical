package com.example.imedical.registration.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.api.ErrorResponse
import com.example.imedical.registration.data.entity.RegistrationForm
import com.example.imedical.registration.data.entity.TokenWrapper
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 8/14/2019.
 */
class ApiCalls @Inject constructor(private val retrofit: Retrofit){
    private val registerApi = retrofit.create(RegistrationApi::class.java)

    suspend fun register(registrationForm: RegistrationForm): ApiResponse<TokenWrapper>{
        try {
            val response = registerApi.register(registrationForm)

            //If successful return body
            if (response.isSuccessful)
                return response.body()!!

            //convert error body if not successful
            val errorConverter: Converter<ResponseBody, ErrorResponse> =
                retrofit.responseBodyConverter(ErrorResponse::class.java, arrayOf())
            val errorBody = errorConverter.convert(response.errorBody()!!)

            return ApiResponse(false, null, errorBody!!.error.joinToString(" \n"))

        } catch (ex: Exception){
            return ApiResponse(false, null, "Check internet connection!")
        }
    }
}