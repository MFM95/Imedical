package com.example.imedical.home.domain.repository

import com.example.imedical.cart.domain.model.CartModel
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.model.ProductModel
import com.example.imedical.home.data.entity.VendorsWrapper
import com.example.imedical.login.domain.model.UserModel

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
interface IProductRepository {
    suspend fun getBestSellers() : DataWrapper<ArrayList<ProductModel>>
    suspend fun getOffers(): DataWrapper<ArrayList<ProductModel>>
    suspend fun getAuthUser(token: String): DataWrapper<UserModel>
    suspend fun addToCart(productId: Int, quantity: Int): DataWrapper<CartModel>
    suspend fun storeWish(id: Int, index: Int): DataWrapper<Int>
    suspend fun checkout(addressId: Int): DataWrapper<Unit>
    suspend fun getVendors(): DataWrapper<VendorsWrapper>
}