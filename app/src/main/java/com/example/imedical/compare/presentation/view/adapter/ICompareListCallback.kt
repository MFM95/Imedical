package com.example.imedical.compare.presentation.view.adapter

import com.example.imedical.compare.domain.model.ProductModel

interface ICompareListCallback {
    fun onProductClick(productModel: ProductModel)
    fun onWishClick(id: Int)
    fun addToCart(id: Int)
}