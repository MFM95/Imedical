package com.example.imedical.home.data.entity

import com.example.imedical.core.api.ProductEntity

/**
 * Created by Ahmed Hassan on 9/5/2019.
 */
data class DealsEntity(
    val dealsOfTheDay: List<ProductEntity>,
    val hotDealOfTheDay: ProductEntity
)