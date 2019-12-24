package com.example.imedical.cart.domain.usecase

import com.example.imedical.cart.domain.model.CartModel
import com.example.imedical.cart.domain.repository.ICartRepository
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import javax.inject.Inject

class RemoveFromCartUseCase @Inject constructor(private val iCartRepository: ICartRepository)
    : BaseUseCase<DataWrapper<CartModel>, RemoveFromCartUseCase.RemoveCartParams>() {

    override suspend fun run(params: RemoveCartParams): DataWrapper<CartModel> {
        return iCartRepository.removeFromCart(params.productRowId)
    }

    data class RemoveCartParams(val productRowId: String)
}