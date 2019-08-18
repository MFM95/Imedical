package com.example.imedical.home.domain.repository

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.home.domain.model.ProductModel

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
interface IProductRepository {
    suspend fun getBestSellers() : DataWrapper<ArrayList<ProductModel>>
    suspend fun getOffers(): DataWrapper<ArrayList<ProductModel>>
}