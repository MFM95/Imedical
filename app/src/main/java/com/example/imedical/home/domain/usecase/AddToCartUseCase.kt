package com.example.imedical.home.domain.usecase

import com.example.imedical.cart.domain.model.CartModel
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import com.example.imedical.home.domain.repository.IProductRepository
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(private val iProductRepository: IProductRepository)
    : BaseUseCase<DataWrapper<CartModel>, AddToCartUseCase.AddCartParams>() {

    override suspend fun run(params: AddCartParams): DataWrapper<CartModel> {
        return iProductRepository.addToCart(params.productId, params.quantity)
    }

    data class AddCartParams(val productId: Int, val quantity: Int)
}