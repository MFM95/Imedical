package com.example.imedical.verification.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.api.ErrorResponse
import com.example.imedical.verification.data.entity.ResendingBody
import com.example.imedical.verification.data.entity.VerificationBody
import com.example.imedical.verification.data.entity.VerificationResponse
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Inject

class ApiCalls @Inject constructor(private val retrofit: Retrofit){
    private val verificationApi = retrofit.create(VerificationApi::class.java)

    suspend fun verify(verificationBody: VerificationBody): ApiResponse<VerificationResponse> {
        try {
            val response = verificationApi.verify(verificationBody)

            //If successful return body
            if (response.isSuccessful)
                return response.body()!!

            //convert error body if not successful
            val errorConverter: Converter<ResponseBody, ErrorResponse> =
                retrofit.responseBodyConverter(ErrorResponse::class.java, arrayOf())
            val errorBody = errorConverter.convert(response.errorBody()!!)

            return ApiResponse(false, null, errorBody!!.error.joinToString(" \n"))


        } catch (ex: Exception){
            return ApiResponse(false, null, ex.message.toString())
        }
    }

    suspend fun resend(resendingBody: ResendingBody): ApiResponse<VerificationResponse> {
        try {
            val response = verificationApi.resend(resendingBody)

            //If successful return body
            if (response.isSuccessful)
                return response.body()!!

            //convert error body if not successful
            val errorConverter: Converter<ResponseBody, ErrorResponse> =
                retrofit.responseBodyConverter(ErrorResponse::class.java, arrayOf())
            val errorBody = errorConverter.convert(response.errorBody()!!)

            return ApiResponse(false, null, errorBody!!.error.joinToString(" \n"))


        } catch (ex: Exception){
            return ApiResponse(false, null, ex.message.toString())
        }
    }


}