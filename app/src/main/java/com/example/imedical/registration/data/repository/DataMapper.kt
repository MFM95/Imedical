package com.example.imedical.registration.data.repository

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.registration.data.entity.TokenWrapper
/**
 * Created by Ahmed Hassan on 8/14/2019.
 */
object DataMapper {
    fun mapRegistrationResponse(apiResponse: ApiResponse<TokenWrapper>) : DataWrapper<String> {
        if(apiResponse.status && apiResponse.data != null)
            return DataWrapper(
                apiResponse.status,
                apiResponse.data.token,
                apiResponse.error
            )

        return DataWrapper(apiResponse.status, null, apiResponse.error)
    }
}