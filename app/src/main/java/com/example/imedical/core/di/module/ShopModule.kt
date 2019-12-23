package com.example.imedical.core.di.module

import com.example.imedical.shop.data.repository.ShopRepository
import com.example.imedical.shop.domain.repository.IShopRepository
import dagger.Provides
import dagger.Module
import javax.inject.Singleton
@Module
class ShopModule {

    @Provides
    fun provideShopResetRepository(shopRepository: ShopRepository)
            : IShopRepository = shopRepository
}