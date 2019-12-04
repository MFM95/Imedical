package com.example.imedical.home.domain.usecase

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import com.example.imedical.core.model.ProductModel
import com.example.imedical.home.domain.repository.IProductRepository
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
class OffersUseCase @Inject constructor(private val iProductRepository: IProductRepository) : BaseUseCase<DataWrapper<ArrayList<ProductModel>>, Unit>(){

    override suspend fun run(params: Unit): DataWrapper<ArrayList<ProductModel>> {
        return iProductRepository.getOffers()
    }

}