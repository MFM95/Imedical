package com.example.imedical.shop.domain.interactor

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.model.ProductModel
import com.example.imedical.core.usecase.BaseUseCase
import com.example.imedical.shop.domain.params.ShopParams
import com.example.imedical.shop.domain.repository.IShopRepository
import javax.inject.Inject

class ShopUseCase @Inject constructor(private val iShopRepository: IShopRepository) :
    BaseUseCase<DataWrapper<List<ProductModel>>, ShopParams>() {
    override suspend fun run(params: ShopParams): DataWrapper<List<ProductModel>> {
        return iShopRepository.getProducts(
            params.minPrice, params.maxPrice,
            params.query, params.brands,
            params.orderBy, params.asc, params.category, params.page)
    }
}