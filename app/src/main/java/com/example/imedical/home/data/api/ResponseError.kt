package com.example.imedical.home.data.api

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.api.ErrorResponse
import com.example.imedical.home.data.entity.ErrorEntity
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Created by Ahmed Hassan on 9/9/2019.
 */
object ResponseError {
    fun <IN, OUT> handle(response: Response<IN>, retrofit: Retrofit): ApiResponse<OUT> {

        val errorConverter: Converter<ResponseBody, ErrorEntity> =
            retrofit.responseBodyConverter(ErrorEntity::class.java, arrayOf())
        val errorBody = errorConverter.convert(response.errorBody()!!)

        var errorStr = errorBody!!.error

        if(errorStr.contains("token_expired") || errorStr.contains("token_not_provided"))
            errorStr = "You must login first to use this feature"

        return ApiResponse(false, null, errorStr)
    }
}