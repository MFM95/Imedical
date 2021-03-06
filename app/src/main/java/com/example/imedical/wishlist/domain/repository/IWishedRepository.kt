package com.example.imedical.wishlist.domain.repository

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.model.ProductModel

/**
 * Created by Ahmed Hassan on 9/7/2019.
 */
interface IWishedRepository {
    suspend fun getWishList(): DataWrapper<List<ProductModel>>
    suspend fun storeWish(id: Int): DataWrapper<Unit>
    suspend fun removeWish(id: Int, index: Int): DataWrapper<Int>

}