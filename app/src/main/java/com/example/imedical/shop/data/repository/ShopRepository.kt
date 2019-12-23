package com.example.imedical.shop.data.repository

import com.example.imedical.core.data.CommonDataMapper
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.model.ProductModel
import com.example.imedical.home.data.entity.BrandEntity
import com.example.imedical.shop.data.api.ShopApiCalls
import com.example.imedical.shop.domain.repository.IShopRepository
import javax.inject.Inject

class ShopRepository @Inject constructor(private val shopApiCalls: ShopApiCalls): IShopRepository {
    override suspend fun getProducts(
        minPrice: Double?,
        maxPrice: Double?,
        query: String?,
        brands: List<Int>?,
        orderBy: String?,
        asc: Boolean?,
        category: Int?,
        page: Int): DataWrapper<List<ProductModel>>{
        return CommonDataMapper.mapProducts(
            shopApiCalls.getProducts(minPrice, maxPrice, query, brands, orderBy, asc, category, page)
        )
    }

    override suspend fun getBrands(): DataWrapper<List<BrandEntity>> {
        return CommonDataMapper.mapBrands(
            shopApiCalls.getBrands())
    }
}