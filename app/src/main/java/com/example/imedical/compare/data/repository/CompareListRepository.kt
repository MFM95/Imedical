package com.example.imedical.compare.data.repository

import com.example.imedical.compare.data.entity.ProductEntity
import com.example.imedical.compare.domain.repository.ICompareListRepository
import com.example.imedical.core.db.ImedicalDatabase
import com.example.imedical.core.model.ProductModel
import javax.inject.Inject

class CompareListRepository @Inject constructor(private val database: ImedicalDatabase)
    : ICompareListRepository {

    override suspend fun getAllProducts(): ArrayList<ProductModel> {
        return DataMapper.mapCompareListProducts(database.compareListDao().getAll())

    }

    override suspend fun addProduct(productModel: ProductModel) {
        database.compareListDao().insert(mapCompareListModel(productModel))
    }

    override suspend fun removeProduct(productModel: ProductModel) {
        database.compareListDao().delete(mapCompareListModel(productModel))
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