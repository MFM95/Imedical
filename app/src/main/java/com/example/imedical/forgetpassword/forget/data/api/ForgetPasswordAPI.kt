package com.example.imedical.forgetpassword.forget.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.forgetpassword.forget.data.entity.ForgetPasswordBody
import com.example.imedical.forgetpassword.forget.data.entity.ForgetPasswordResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ForgetPasswordAPI {
    @POST("auth/forget")
    suspend fun forget(@Body forgetPasswordBody: ForgetPasswordBody)
            : Response<ApiResponse<ForgetPasswordResponse>>

}