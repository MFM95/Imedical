package com.example.imedical.core.di.module

import com.example.imedical.categories.data.repository.CategoriesRepositoryImpl
import com.example.imedical.categories.domain.repository.ICategoriesRepository
import com.example.imedical.verification.data.repository.VerificationRepositoryImpl
import com.example.imedical.verification.domain.repository.IVerificationRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class CategoriesModule {
    @Provides
    @Singleton
    fun provideCategoriesRepository(categoriesRepositoryImpl: CategoriesRepositoryImpl) : ICategoriesRepository =
        categoriesRepositoryImpl
}