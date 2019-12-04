package com.example.imedical.compare.domain.repository

import com.example.imedical.compare.domain.model.ProductModel

interface ICompareListRepository {
    suspend fun getAllProducts(): ArrayList<ProductModel>
    suspend fun addProduct(productModel: ProductModel)
    suspend fun removeProduct(productModel: ProductModel)
}