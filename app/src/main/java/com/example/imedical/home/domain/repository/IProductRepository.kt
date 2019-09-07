package com.example.imedical.home.domain.repository

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.model.ProductModel
import com.example.imedical.login.domain.model.UserModel

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
interface IProductRepository {
    suspend fun getBestSellers() : DataWrapper<ArrayList<ProductModel>>
    suspend fun getOffers(): DataWrapper<ArrayList<ProductModel>>
    suspend fun getAuthUser(token: String): DataWrapper<UserModel>
}