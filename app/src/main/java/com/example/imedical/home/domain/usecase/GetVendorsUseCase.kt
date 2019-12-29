package com.example.imedical.home.domain.usecase

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import com.example.imedical.home.data.entity.VendorsWrapper
import com.example.imedical.home.domain.repository.IProductRepository
import javax.inject.Inject

class GetVendorsUseCase @Inject constructor(private val iProductRepository: IProductRepository): BaseUseCase<DataWrapper<VendorsWrapper>, Unit>() {
    override suspend fun run(params: Unit): DataWrapper<VendorsWrapper> {
        return iProductRepository.getVendors()
    }
}