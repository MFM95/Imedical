package com.example.imedical.core.model

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
data class ProductModel (
    val id: Int,
    val name: String,
    val imageUrl: String,
    val price: Double,
    val salePrice: Double?,
    var inWishList: Boolean,
    val inCompareList: Boolean,
    val brand: String?,
    val quantity: Int
)