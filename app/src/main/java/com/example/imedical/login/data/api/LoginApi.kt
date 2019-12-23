package com.example.imedical.login.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.login.data.entity.Credentials
import com.example.imedical.login.data.entity.TokenWrapper
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
interface LoginApi {
    @POST("auth/login")
    suspend fun login(@Body credentials: Credentials)
            : Response<ApiResponse<TokenWrapper>>

}