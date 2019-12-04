package com.example.imedical.forgetpassword.resetpassword.data.repository

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.forgetpassword.resetpassword.data.entity.ResetResponse

object DataMapper {
    fun mapResetResponse(apiResponse: ApiResponse<ResetResponse>): DataWrapper<String> {
        if(apiResponse.status != null && apiResponse.status && apiResponse.data != null)
            return DataWrapper(
                apiResponse.status,
                apiResponse.data.token,
                apiResponse.error
            )

        return DataWrapper(apiResponse.status?:false, null, apiResponse.error)
    }
}