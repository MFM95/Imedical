package com.example.imedical.home.presentation.view.adapter

import com.example.imedical.core.model.ProductModel

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
interface IProductCallback {
    fun onProductClick(productModel: ProductModel)
    fun onWishClick(id: Int, index: Int)
    fun onCompareClick(id: Int)
    fun addToCart(id: Int)
}