package com.example.imedical.categories.domain.repository

import com.example.imedical.categories.domain.model.CategoryModel
import com.example.imedical.core.model.DataWrapper

interface ICategoriesRepository {
    suspend fun getAllCategories(): DataWrapper<ArrayList<CategoryModel>>
    suspend fun getCategoryChildren(id: Int): DataWrapper<ArrayList<CategoryModel>>
}