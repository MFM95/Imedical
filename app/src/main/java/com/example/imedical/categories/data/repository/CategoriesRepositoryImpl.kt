package com.example.imedical.categories.data.repository

import com.example.imedical.categories.data.api.APICalls
import com.example.imedical.categories.data.api.CategoriesAPI
import com.example.imedical.categories.domain.model.CategoryModel
import com.example.imedical.categories.domain.repository.ICategoriesRepository
import com.example.imedical.core.model.DataWrapper
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(private val apiCalls: APICalls)
    : ICategoriesRepository {
    override suspend fun getAllCategories(): DataWrapper<ArrayList<CategoryModel>> {
       return DataMapper.mapCategoriesResponse(apiCalls.getAllCategories())
    }

    override suspend fun getCategoryChildren(id: Int): DataWrapper<ArrayList<CategoryModel>> {
        return DataMapper.mapCategoriesResponse(apiCalls.getCategoryChildren(id))
    }
}