package com.example.imedical.core.api

import com.example.imedical.home.data.entity.BrandEntity
import com.google.gson.annotations.SerializedName

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
data class ProductEntity(
    val id: Int,
    val name: String,
    val cover: String,
    val price: Double,
    @SerializedName("sale_price") val salePrice: Double?,
    @SerializedName("in_wish_list") val inWishList: Boolean,
    @SerializedName("in_compare_list") val inCompareList: Boolean,
    val brand: BrandEntity?,
    val quantity: Int
)