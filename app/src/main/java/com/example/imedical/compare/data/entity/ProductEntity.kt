package com.example.imedical.compare.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(

    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "cover") val cover: String,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "sale_price") val salePrice: Double?,
    @ColumnInfo(name = "in_wish_list") val inWishList: Boolean,
    @ColumnInfo(name = "in_compare_list") val inCompareList: Boolean,
    @ColumnInfo(name = "brand") val brand: String?,
    @ColumnInfo(name = "quantity") val quantity: Int
)