package com.example.imedical.core.data

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.model.DataWrapper

object CommonDataMapper {

    fun mapEmpty(apiResponse: ApiResponse<Unit>?): DataWrapper<Unit> {
        apiResponse?.let {
            return DataWrapper(it.status?:false, it.data, it.error)
        }
        return DataWrapper(false, Unit, "Server Error!")
    }
}