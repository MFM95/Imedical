package com.example.imedical.core.di.module

import com.example.imedical.cart.data.repository.CartRepository
import com.example.imedical.cart.domain.repository.ICartRepository
import dagger.Module
import dagger.Provides

@Module
class CartModule {
    @Provides
    fun provideCartRepository(cartRepository: CartRepository): ICartRepository{
        return cartRepository
    }
}