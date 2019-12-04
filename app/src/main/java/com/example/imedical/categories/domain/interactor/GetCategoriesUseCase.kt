package com.example.imedical.categories.domain.interactor

import com.example.imedical.categories.domain.model.CategoryModel
import com.example.imedical.categories.domain.repository.ICategoriesRepository
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val iCategoriesRepository: ICategoriesRepository)
    : BaseUseCase<DataWrapper<ArrayList<CategoryModel>>, Unit>(){
    override suspend fun run(params: Unit): DataWrapper<ArrayList<CategoryModel>> {
        return iCategoriesRepository.getAllCategories()
    }
}