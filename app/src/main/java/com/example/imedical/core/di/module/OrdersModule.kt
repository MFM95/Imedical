package com.example.imedical.core.di.module

import com.example.imedical.orders.data.repository.OrdersRepository
import com.example.imedical.orders.domain.repository.IOrdersRepository
import dagger.Module
import dagger.Provides

@Module
class OrdersModule {
    @Provides
    fun provideOrdersRepository(ordersRepository: OrdersRepository): IOrdersRepository =
        ordersRepository
}