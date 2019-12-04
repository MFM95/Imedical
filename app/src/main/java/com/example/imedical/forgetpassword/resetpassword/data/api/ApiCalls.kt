package com.example.imedical.forgetpassword.resetpassword.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.api.ErrorResponse
import com.example.imedical.forgetpassword.resetpassword.data.entity.ResetBody
import com.example.imedical.forgetpassword.resetpassword.data.entity.ResetResponse
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Inject

class ApiCalls @Inject constructor(private val retrofit: Retrofit) {
    private val resetApi = retrofit.create(ResetApi::class.java)

    suspend fun reset(resetBody: ResetBody): ApiResponse<ResetResponse> {
        try {
            val response = resetApi.reset(resetBody)

            if (response.isSuccessful)
                return response.body()!!

            val errorConverter: Converter<ResponseBody, ErrorResponse> =
                retrofit.responseBodyConverter(ErrorResponse::class.java, arrayOf())
            val errorBody = errorConverter.convert(response.errorBody()!!)
            return ApiResponse(false, null, errorBody!!.error.joinToString(". \n"))

        } catch (ex: Exception) {
            return ApiResponse(false, null, ex.message!!)
        }
    }
}