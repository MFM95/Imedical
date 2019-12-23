package com.example.imedical.shop.domain.interactor

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import com.example.imedical.home.data.entity.BrandEntity
import com.example.imedical.shop.domain.repository.IShopRepository
import javax.inject.Inject

class GetBrandsUseCase @Inject constructor(private val iShopRepository: IShopRepository)
    : BaseUseCase<DataWrapper<List<BrandEntity>>, Unit>() {

    override suspend fun run(params: Unit): DataWrapper<List<BrandEntity>> {
        return iShopRepository.getBrands()
    }
}