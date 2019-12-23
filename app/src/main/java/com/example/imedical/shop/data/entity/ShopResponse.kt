package com.example.imedical.shop.data.entity

import com.example.imedical.core.api.ProductEntity
import com.google.gson.annotations.SerializedName

data class ShopResponse(
    @SerializedName("products") val products: List<ProductEntity>
)