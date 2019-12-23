package com.example.imedical.shop.domain.repository

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.model.ProductModel
import com.example.imedical.home.data.entity.BrandEntity

interface IShopRepository {
    suspend fun getProducts(
        minPrice: Double?,
        maxPrice: Double?,
        query: String?,
        brands: List<Int>?,
        orderBy: String?,
        asc: Boolean?,
        category: Int?,
        page: Int) : DataWrapper<List<ProductModel>>

    suspend fun getBrands(): DataWrapper<List<BrandEntity>>
}