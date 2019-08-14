package com.example.imedical.login.data.repository

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.login.data.entity.TokenWrapper
import com.example.imedical.login.domain.model.DataWrapper

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
object DataMapper {
    fun mapLoginData(apiResponse: ApiResponse<TokenWrapper>) : DataWrapper<String> {
        if(apiResponse.status)
            return DataWrapper(apiResponse.status, apiResponse.data.token, apiResponse.error)

        return DataWrapper(apiResponse.status, null, apiResponse.error)
    }

}