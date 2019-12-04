package com.example.imedical.login.data.repository

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.login.data.entity.TokenWrapper

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
object DataMapper {
    private const val INVALID_CREDENTIALS = "invalid_credentials"

    fun mapLoginData(apiResponse: ApiResponse<TokenWrapper>) : DataWrapper<String> {
        if(apiResponse.status != null && apiResponse.status && apiResponse.data != null)
            return DataWrapper(
                apiResponse.status,
                apiResponse.data.token,
                apiResponse.error
            )

        var error = apiResponse.error
        when(apiResponse.error){
            INVALID_CREDENTIALS -> error = "Invalid credentials."
        }
        return DataWrapper(apiResponse.status?:false, null, error)
    }

}