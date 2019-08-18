package com.example.imedical.home.domain.model

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
data class ProductModel (
    val id: Int,
    val name: String,
    val imageUrl: String,
    val price: Double,
    val inWishList: Boolean,
    val inCompareList: Boolean,
    val category: String,
    val brand: String,
    val quantity: Int
)