package com.example.imedical.home.data.repository

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.model.ProductModel
import com.example.imedical.home.data.api.ApiCalls
import com.example.imedical.home.domain.repository.IProductRepository
import com.example.imedical.login.domain.model.UserModel
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
class ProductRepository @Inject constructor(private val apiCalls: ApiCalls) : IProductRepository{

    override suspend fun getOffers(): DataWrapper<ArrayList<ProductModel>> {
        return DataMapper.mapDeals(apiCalls.getOffers().body())
    }

    override suspend fun getBestSellers(): DataWrapper<ArrayList<ProductModel>> {
        return DataMapper.mapBestSellers(apiCalls.getBestSellers().body())
    }

    override suspend fun getAuthUser(token: String): DataWrapper<UserModel> {
        return DataMapper.mapUser(apiCalls.getAuthUser().body())
    }

    override suspend fun addToCart(productId: Int, quantity: Int): DataWrapper<Unit> {
        return DataMapper.mapEmpty(apiCalls.addToCart(productId, quantity))
    }
}