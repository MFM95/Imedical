package com.example.imedical.orders.domain.interactor

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import com.example.imedical.orders.domain.model.OrdersWrapperModel
import com.example.imedical.orders.domain.repository.IOrdersRepository
import javax.inject.Inject

class GetOrdersUseCase @Inject constructor(private val iOrdersRepository: IOrdersRepository):BaseUseCase<DataWrapper<OrdersWrapperModel>, Int>(){

    override suspend fun run(params: Int): DataWrapper<OrdersWrapperModel> {
        return iOrdersRepository.getOrders(params)
    }
}