package com.example.imedical.forgetpassword.resetpassword.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.forgetpassword.resetpassword.data.entity.ResetBody
import com.example.imedical.forgetpassword.resetpassword.data.entity.ResetResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ResetApi {
    @POST("auth/reset")
    suspend fun reset(@Body resetBody: ResetBody)
            : Response<ApiResponse<ResetResponse>>

}