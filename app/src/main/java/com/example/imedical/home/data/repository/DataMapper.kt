package com.example.imedical.home.data.repository

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.home.data.entity.DealsEntity
import com.example.imedical.home.data.entity.ProductEntity
import com.example.imedical.home.data.entity.UserEntity
import com.example.imedical.home.domain.model.ProductModel
import com.example.imedical.login.domain.model.UserModel

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
object DataMapper {
    fun mapDeals(entitiesWrapper: ApiResponse<DealsEntity>?): DataWrapper<ArrayList<ProductModel>> {
        if (entitiesWrapper?.data == null)
            return DataWrapper(false, null, entitiesWrapper!!.error)

        val results = ArrayList<ProductModel>()
        results.add(mapSingleProduct(entitiesWrapper.data.hotDealOfTheDay))

        for (entity in entitiesWrapper.data.dealsOfTheDay)
            results.add(
                mapSingleProduct(entity)
            )

        return DataWrapper(entitiesWrapper.status, results, entitiesWrapper.error)
    }

    fun mapBestSellers(entitiesWrapper: ApiResponse<List<List<ProductEntity>>>?): DataWrapper<ArrayList<ProductModel>> {
        if (entitiesWrapper?.data == null)
            return DataWrapper(false, null, entitiesWrapper!!.error)

        val results = ArrayList<ProductModel>()

        for (entity in entitiesWrapper.data[0])
            results.add(
                mapSingleProduct(entity)
            )

        return DataWrapper(entitiesWrapper.status, results, entitiesWrapper.error)
    }

    fun mapUser(response: ApiResponse<UserEntity>?): DataWrapper<UserModel> {
        var model: UserModel? = null

        if (response?.data != null)
            model = UserModel(
                response.data.id,
                response.data.name,
                response.data.email,
                response.data.mobile
            )

        return DataWrapper(response!!.status, model, response.error)
    }

    fun mapSingleProduct(entity: ProductEntity): ProductModel {
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