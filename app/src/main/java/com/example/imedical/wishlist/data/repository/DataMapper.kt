package com.example.imedical.wishlist.data.repository

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.home.data.entity.DealsEntity
import com.example.imedical.core.api.ProductEntity
import com.example.imedical.home.data.entity.UserEntity
import com.example.imedical.core.model.ProductModel
import com.example.imedical.login.domain.model.UserModel

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
object DataMapper {

    fun mapWishList(entitiesWrapper: ApiResponse<List<ProductEntity>>?): DataWrapper<List<ProductModel>> {
        if (entitiesWrapper?.data == null)
            return DataWrapper(false, null, entitiesWrapper!!.error)

        val results = ArrayList<ProductModel>()

        for (entity in entitiesWrapper.data)
            results.add(
                mapSingleProduct(entity)
            )

        return DataWrapper(entitiesWrapper.status, results, entitiesWrapper.error)
    }

    private fun mapSingleProduct(entity: ProductEntity): ProductModel {
        return ProductModel(
            entity.id,
            entity.name,
            entity.cover,
            entity.price,
            entity.salePrice,
            entity.inWishList,
            entity.inCompareList,
            entity.brand?.name,
            entity.quantity
        )
    }
}