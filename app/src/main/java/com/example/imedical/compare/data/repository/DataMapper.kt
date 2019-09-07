package com.example.imedical.compare.data.repository

import com.example.imedical.compare.data.entity.ProductEntity
import com.example.imedical.compare.domain.model.ProductModel
import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.login.domain.model.UserModel


object DataMapper {
    fun mapCompareListProducts(entitiesWrapper: List<ProductEntity>?): ArrayList<ProductModel> {

        val results = ArrayList<ProductModel>()

        entitiesWrapper?.let {
            for (entity in it)
                results.add(
                    mapSingleProduct(entity)
                )
        }
        return results
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
            entity.brand,
            entity.quantity
        )
    }
}