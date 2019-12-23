package com.example.imedical.compare.domain.model


data class ProductModel (
    val id: Int,
    val name: String,
    val imageUrl: String,
    val price: Double,
    val salePrice: Double?,
    val inWishList: Boolean,
    val inCompareList: Boolean,
    val brand: String?,
    val quantity: Int
)