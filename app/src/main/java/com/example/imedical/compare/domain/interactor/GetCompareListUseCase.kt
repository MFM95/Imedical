package com.example.imedical.compare.domain.interactor

import com.example.imedical.compare.domain.model.ProductModel
import com.example.imedical.compare.domain.repository.ICompareListRepository
import com.example.imedical.core.usecase.BaseUseCase
import javax.inject.Inject

class GetCompareListUseCase @Inject constructor(private val iCompareListRepository: ICompareListRepository)
    : BaseUseCase<ArrayList<ProductModel>, Unit>() {
    override suspend fun run(params: Unit): ArrayList<ProductModel> {
        return iCompareListRepository.getAllProducts()
    }
}