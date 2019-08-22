package com.example.imedical.verification.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.verification.data.entity.ResendingBody
import com.example.imedical.verification.data.entity.VerificationBody
import com.example.imedical.verification.data.entity.VerificationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface VerificationApi {
    @POST("auth/activate")
    suspend fun verify(@Body verificationBody: VerificationBody)
            : Response<ApiResponse<VerificationResponse>>

    @POST("auth/resend-code")
    suspend fun resend(@Body resendingBody: ResendingBody)
            : Response<ApiResponse<VerificationResponse>>
}