package com.example.imedical.login.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.api.ErrorResponse
import com.example.imedical.home.data.api.ResponseError
import com.example.imedical.login.data.entity.Credentials
import com.example.imedical.login.data.entity.TokenWrapper
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
class ApiCalls @Inject constructor(private val retrofit: Retrofit){

    private val loginApi = retrofit.create(LoginApi::class.java)

    suspend fun login(credentials: Credentials): ApiResponse<TokenWrapper> {
        try {
            val response = loginApi.login(credentials)

            //If successful return body
            if (response.isSuccessful)
                return response.body()!!

            //convert error body if not successful
            return ResponseError.handle(response, retrofit)

        } catch (ex: Exception){
            return ApiResponse(false, null, "Check internet connection!")
        }
    }
}