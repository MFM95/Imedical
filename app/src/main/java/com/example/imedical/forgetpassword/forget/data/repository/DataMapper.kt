package com.example.imedical.forgetpassword.forget.data.repository

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.forgetpassword.forget.data.entity.ForgetPasswordResponse


object DataMapper {
    fun mapForgetPasswordResponse(apiResponse: ApiResponse<ForgetPasswordResponse>) : DataWrapper<String> {
        if(apiResponse.status && apiResponse.data != null)
            return DataWrapper(
                apiResponse.status,
                apiResponse.data.message,
                apiResponse.error
            )

        return DataWrapper(apiResponse.status, null, apiResponse.error)
    }
}