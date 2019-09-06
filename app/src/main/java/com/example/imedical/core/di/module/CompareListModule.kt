package com.example.imedical.core.di.module

import com.example.imedical.compare.data.repository.CompareListRepository
import com.example.imedical.compare.domain.repository.ICompareListRepository
import com.example.imedical.home.data.repository.ProductRepository
import com.example.imedical.home.domain.repository.IProductRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class CompareListModule {

    @Provides
    @Singleton
    fun provideCompareListRepository(compareListRepository: CompareListRepository): ICompareListRepository
            = compareListRepository
}