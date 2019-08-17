package com.example.imedical.registration.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.registration.data.entity.RegistrationForm
import com.example.imedical.registration.data.entity.TokenWrapper
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Ahmed Hassan on 8/14/2019.
 */
interface RegistrationApi {
    @POST("auth/register")
    suspend fun register(@Body registrationForm: RegistrationForm)
            : Response<ApiResponse<TokenWrapper>>

}