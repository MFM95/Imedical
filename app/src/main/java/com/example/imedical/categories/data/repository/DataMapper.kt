package com.example.imedical.categories.data.repository

import com.example.imedical.categories.data.entity.Category
import com.example.imedical.categories.data.entity.GetCategoriesResponse
import com.example.imedical.categories.domain.model.CategoryModel
import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.model.DataWrapper

object DataMapper {
    fun mapCategoriesResponse(apiResponse: ApiResponse<GetCategoriesResponse>)
            : DataWrapper<ArrayList<CategoryModel>> {
        if (apiResponse.status == false || apiResponse.data == null)
            return DataWrapper(apiResponse.status!!, null, apiResponse.error)

        val result = ArrayList<CategoryModel>()

        for(category in apiResponse.data.categories) {
            result.add(mapCategory(category))
        }

        return DataWrapper(apiResponse.status!!, result, apiResponse.error)
    }

    private fun mapCategory(category: Category): CategoryModel {
        return CategoryModel(
            category.id,
            category.name,
            category.childrenCount)
    }
}