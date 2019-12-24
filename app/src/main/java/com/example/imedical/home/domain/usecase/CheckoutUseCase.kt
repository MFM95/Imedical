package com.example.imedical.home.domain.usecase

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import com.example.imedical.home.domain.repository.IProductRepository
import javax.inject.Inject

class CheckoutUseCase @Inject constructor(private val iProductRepository: IProductRepository) : BaseUseCase<DataWrapper<Unit>, Int>() {

    override suspend fun run(params: Int): DataWrapper<Unit> {
        return iProductRepository.checkout(params)
    }
}