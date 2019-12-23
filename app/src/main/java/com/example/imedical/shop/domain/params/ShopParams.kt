package com.example.imedical.shop.domain.params

data class ShopParams(val minPrice: Double?,
                      val maxPrice: Double?,
                      val query: String?,
                      val brands: List<Int>?,
                      val orderBy: String?,
                      val asc: Boolean?,
                      val category: Int?,
                      var page: Int)