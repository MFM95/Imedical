package com.example.imedical.compare.data.repository

import com.example.imedical.compare.data.entity.BrandEntity
import com.example.imedical.compare.data.entity.ProductEntity
import com.example.imedical.compare.domain.model.ProductModel
import com.example.imedical.compare.domain.repository.ICompareListRepository
import com.example.imedical.core.db.ImedicalDatabase
import javax.inject.Inject
import kotlin.collections.ArrayList

class CompareListRepository @Inject constructor(private val database: ImedicalDatabase)
    : ICompareListRepository {
    override suspend fun getAllProducts(): ArrayList<ProductModel> {
        return DataMapper.mapCompareListProducts(database.compareListDao().getAll())

    }

    override suspend fun addProduct(productModel: ProductModel) {
        database.compareListDao().insert(mapCompareListModel(productModel))
    }

    private fun mapCompareListModel(model: ProductModel): ProductEntity {
        return ProductEntity(
            model.id,
            model.name,
            model.imageUrl,
            model.price,
            model.salePrice,
            model.inWishList,
            model.inCompareList,
            model.brand,
            model.quantity
        )
    }
}