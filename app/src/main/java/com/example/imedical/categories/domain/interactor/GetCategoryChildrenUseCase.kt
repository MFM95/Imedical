package com.example.imedical.categories.domain.interactor

import com.example.imedical.categories.domain.model.CategoryModel
import com.example.imedical.categories.domain.repository.ICategoriesRepository
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import javax.inject.Inject

class GetCategoryChildrenUseCase @Inject constructor(private val iCategoriesRepository: ICategoriesRepository)
    : BaseUseCase<Pair<Int, DataWrapper<ArrayList<CategoryModel>>>, Int>(){
    override suspend fun run(params: Int): Pair<Int, DataWrapper<ArrayList<CategoryModel>>>{
        return Pair(params, iCategoriesRepository.getCategoryChildren(params))
    }
}