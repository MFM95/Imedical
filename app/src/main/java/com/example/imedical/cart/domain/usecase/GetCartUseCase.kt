package com.example.imedical.cart.domain.usecase

import com.example.imedical.cart.domain.model.CartModel
import com.example.imedical.cart.domain.repository.ICartRepository
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import javax.inject.Inject

class GetCartUseCase @Inject constructor(private val iCartRepository: ICartRepository)
    : BaseUseCase<DataWrapper<CartModel>, Unit>() {

    override suspend fun run(params: Unit): DataWrapper<CartModel> {
        return iCartRepository.getCart()
    }
}