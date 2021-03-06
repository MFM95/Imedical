package com.example.imedical.categories.data.api

import com.example.imedical.categories.data.entity.GetCategoriesResponse
import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.api.ErrorResponse
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Inject

class APICalls @Inject constructor(private val retrofit: Retrofit) {

    private val api = retrofit.create(CategoriesAPI::class.java)

    suspend fun getAllCategories(): ApiResponse<GetCategoriesResponse> {
        try {
            val response = api.getAllCategories()

            //If successful return body
            if (response.isSuccessful)
                return response.body()!!

            //convert error body if not successful
            val errorConverter: Converter<ResponseBody, ErrorResponse> =
                retrofit.responseBodyConverter(ErrorResponse::class.java, arrayOf())
            val errorBody = errorConverter.convert(response.errorBody()!!)

            return ApiResponse(false, null, errorBody!!.error.joinToString(". \n"))

        } catch (ex: Exception){
            return ApiResponse(false, null, ex.message.toString())
        }


    }

}