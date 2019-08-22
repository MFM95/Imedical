package com.example.imedical.forgetpassword.forget.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.api.ErrorResponse
import com.example.imedical.forgetpassword.forget.data.entity.ForgetPasswordBody
import com.example.imedical.forgetpassword.forget.data.entity.ForgetPasswordResponse
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.Exception
import javax.inject.Inject

class ApiCalls @Inject constructor(private val retrofit: Retrofit) {
    private val forgetPasswordAPI = retrofit.create(ForgetPasswordAPI::class.java)

    suspend fun forget(forgetPasswordBody: ForgetPasswordBody)
            : ApiResponse<ForgetPasswordResponse> {
        try {
            val response = forgetPasswordAPI.forget(forgetPasswordBody)

            //If successful return body
            if (response.isSuccessful)
                return response.body()!!

            //convert error body if not successful
            val errorConverter: Converter<ResponseBody, ErrorResponse> =
                retrofit.responseBodyConverter(ErrorResponse::class.java, arrayOf())
            val errorBody = errorConverter.convert(response.errorBody()!!)

            return ApiResponse(false, null, errorBody!!.error.joinToString(" \n"))


        } catch (ex: Exception) {
            return ApiResponse(false, null, ex.message.toString())
        }
    }
}



