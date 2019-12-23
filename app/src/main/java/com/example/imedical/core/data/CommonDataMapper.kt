package com.example.imedical.core.data

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.api.ProductEntity
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.model.ProductModel
import com.example.imedical.home.data.entity.BrandEntity
import com.example.imedical.shop.data.entity.BrandsResponse
import com.example.imedical.shop.data.entity.ShopResponse

object CommonDataMapper {

    fun mapEmpty(apiResponse: ApiResponse<Unit>?): DataWrapper<Unit> {
        apiResponse?.let {
            return DataWrapper(it.status?:false, it.data, it.error)
        }
        return DataWrapper(false, Unit, "Server Error!")
    }

    fun mapProducts(entitiesWrapper: ApiResponse<ShopResponse>?): DataWrapper<List<ProductModel>>{
        if (entitiesWrapper?.data == null)
            return DataWrapper(false, null, entitiesWrapper?.error?:"Server error")

        val results = ArrayList<ProductModel>()

        for (entity in entitiesWrapper.data.products)
            results.add(
                mapSingleProduct(entity)
            )

        return DataWrapper(entitiesWrapper.status?:false, results, entitiesWrapper.error)

    }

    fun mapBrands(entitiesWrapper: ApiResponse<BrandsResponse>?): DataWrapper<List<BrandEntity>>{
        if (entitiesWrapper?.data == null)
            return DataWrapper(false, null, entitiesWrapper?.error?:"Server error")

        val results = ArrayList<BrandEntity>()

        for (entity in entitiesWrapper.data.brands)
            results.add(entity)

        return DataWrapper(entitiesWrapper.status?:false, results, entitiesWrapper.error)

    }
    private fun mapSingleProduct(entity: ProductEntity): ProductModel {
        return ProductModel(
            entity.id,
            entity.name,
            entity.description,
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