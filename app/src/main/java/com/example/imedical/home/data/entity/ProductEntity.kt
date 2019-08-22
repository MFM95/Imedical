package com.example.imedical.home.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
data class ProductEntity(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val price: Double,
    @SerializedName("in_wish_list") val inWishList: Boolean,
    @SerializedName("in_compare_list") val inCompareList: Boolean,
    val category: String,
    val brand: String,
    val quantity: Int
)