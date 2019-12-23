package com.example.imedical.wishlist.data.repository

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.model.ProductModel
import com.example.imedical.wishlist.data.api.ApiCalls
import com.example.imedical.wishlist.domain.repository.IWishedRepository
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 9/7/2019.
 */
class WishedRepository @Inject constructor(private val apiCalls: ApiCalls) : IWishedRepository {

    override suspend fun getWishList(): DataWrapper<List<ProductModel>> {
        return DataMapper.mapWishList(apiCalls.getWishList())
    }

    override suspend fun storeWish(id: Int): DataWrapper<Unit>{
        return DataMapper.mapEmpty(apiCalls.storeWish(id))
    }

    override suspend fun removeWish(id: Int, index: Int): DataWrapper<Int>{
        return DataMapper.mapRemoveWish(apiCalls.removeWish(id) ,index)
    }
}