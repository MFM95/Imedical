package com.example.imedical.home.data.repository

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.home.data.entity.DealsEntity
import com.example.imedical.core.api.ProductEntity
import com.example.imedical.home.data.entity.UserEntity
import com.example.imedical.core.model.ProductModel
import com.example.imedical.home.data.entity.UserWrapperEntity
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

        return DataWrapper(entitiesWrapper.status?:false, results, entitiesWrapper.error)
    }

    fun mapBestSellers(entitiesWrapper: ApiResponse<List<List<ProductEntity>>>?): DataWrapper<ArrayList<ProductModel>> {
        if (entitiesWrapper?.data == null)
            return DataWrapper(false, null, entitiesWrapper!!.error)

        val results = ArrayList<ProductModel>()

        for (entity in entitiesWrapper.data[0])
            results.add(
                mapSingleProduct(entity)
            )

        return DataWrapper(entitiesWrapper.status?:false, results, entitiesWrapper.error)
    }

    fun mapUser(response: ApiResponse<UserWrapperEntity>?): DataWrapper<UserModel> {
        var model: UserModel? = null

        if (response?.data != null)
            model = UserModel(
                response.data.user.id,
                response.data.user.name,
                response.data.user.email,
                response.data.user.mobile
            )
        if(response == null){
            return DataWrapper(false, null, "Something wend wrong")
        }
        return DataWrapper(response.status?:false, model, response.error)
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

    fun mapEmpty(apiResponse: ApiResponse<Unit>?): DataWrapper<Unit>{
        apiResponse?.let {
            return DataWrapper(it.status?:false, it.data, it.error)
        }
        return DataWrapper(false, Unit, "Login to be able to use that feature")
    }
}