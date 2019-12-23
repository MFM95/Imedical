package com.example.imedical.verification.data.repository

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.verification.data.entity.VerificationResponse


object DataMapper {
    fun mapVerificationResponse(apiResponse: ApiResponse<VerificationResponse>) : DataWrapper<String> {
        if(apiResponse.status != null && apiResponse.status && apiResponse.data != null)
            return DataWrapper(
                apiResponse.status,
                apiResponse.data.message,
                apiResponse.error
            )

        return DataWrapper(apiResponse.status?:false, null, apiResponse.error)
    }
}