package com.example.imedical.home.data.repository

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.home.data.entity.ProductEntity
import com.example.imedical.home.domain.model.ProductModel

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
object DataMapper {
    fun mapProduct(entitiesWrapper: ApiResponse<List<ProductEntity>>?): DataWrapper<ArrayList<ProductModel>>{
        if(entitiesWrapper?.data == null)
            return DataWrapper(false, null, entitiesWrapper!!.error)

        val results = ArrayList<ProductModel>()
        for (entity in entitiesWrapper.data)
            results.add(
                ProductModel(
                entity.id,
                entity.name,
                entity.imageUrl,
                entity.price,
                entity.inWishList,
                entity.inCompareList,
                entity.category,
                entity.brand,
                entity.quantity
            )
            )

        return DataWrapper(entitiesWrapper.status, results, entitiesWrapper.error)
    }
}