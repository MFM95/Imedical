package com.example.imedical.wishlist.presentation.view.adapter.callback

import com.example.imedical.core.model.ProductModel

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
interface IWishCallback {
    fun onProductClick(productModel: ProductModel)
    fun onRemoveClick(id: Int, index: Int)
    fun onCompareClick(id: Int)
    fun addToCart(id: Int)
}