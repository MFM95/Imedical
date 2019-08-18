package com.example.imedical.home.data.repository

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.home.data.api.ApiCalls
import com.example.imedical.home.domain.model.ProductModel
import com.example.imedical.home.domain.repository.IProductRepository
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
class ProductRepository @Inject constructor(private val apiCalls: ApiCalls) : IProductRepository{

    override suspend fun getOffers(): DataWrapper<ArrayList<ProductModel>> {
        return DataMapper.mapProduct(apiCalls.getOffers().body())
    }

    override suspend fun getBestSellers(): DataWrapper<ArrayList<ProductModel>> {
        return DataMapper.mapProduct(apiCalls.getBestSellers().body())
    }

}