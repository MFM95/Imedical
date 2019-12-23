package com.example.imedical.compare.domain.interactor

import com.example.imedical.compare.domain.repository.ICompareListRepository
import com.example.imedical.core.model.ProductModel
import com.example.imedical.core.usecase.BaseUseCase
import javax.inject.Inject

class AddToCompareListUseCase @Inject constructor(private val iCompareListRepository: ICompareListRepository)
    : BaseUseCase<Unit, ProductModel>() {
    override suspend fun run(params:ProductModel) {
        iCompareListRepository.addProduct(params)
    }
}