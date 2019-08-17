package com.example.imedical.verification.data.repository

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.verification.data.entity.VerificationResponse

/**
 * Created by Ahmed Hassan on 8/14/2019.
 */
object DataMapper {
    fun mapVerificationResponse(apiResponse: ApiResponse<VerificationResponse>) : DataWrapper<String> {
        if(apiResponse.status && apiResponse.data != null)
            return DataWrapper(
                apiResponse.status,
                apiResponse.data.message,
                apiResponse.error
            )

        return DataWrapper(apiResponse.status, null, apiResponse.error)
    }
}