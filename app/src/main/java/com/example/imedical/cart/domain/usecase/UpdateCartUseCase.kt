package com.example.imedical.cart.domain.usecase

import com.example.imedical.cart.domain.model.CartModel
import com.example.imedical.cart.domain.repository.ICartRepository
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import javax.inject.Inject

class UpdateCartUseCase @Inject constructor(private val iCartRepository: ICartRepository)
    : BaseUseCase<DataWrapper<CartModel>, UpdateCartUseCase.UpdateCartParams>() {

    override suspend fun run(params: UpdateCartParams): DataWrapper<CartModel> {
        return iCartRepository.updateCartItem(params.productRowId, params.quantity)
    }

    data class UpdateCartParams(val productRowId: String, val quantity: Int)
}