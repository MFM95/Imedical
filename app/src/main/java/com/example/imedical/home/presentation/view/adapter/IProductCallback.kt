package com.example.imedical.home.presentation.view.adapter

import com.example.imedical.home.domain.model.ProductModel

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
interface IProductCallback {
    fun onProductClick(productModel: ProductModel)
    fun onWishClick(id: Int)
    fun onCompareClick(productModel: ProductModel)
    fun addToCart(id: Int)
}