package com.example.imedical.core.di.module

import com.example.imedical.home.data.repository.ProductRepository
import com.example.imedical.home.domain.repository.IProductRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */

@Module
class ProductModule {

    @Provides
    @Singleton
    fun provideProductRepository(productRepository: ProductRepository): IProductRepository
            = productRepository
}