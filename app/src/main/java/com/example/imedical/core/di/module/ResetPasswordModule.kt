package com.example.imedical.core.di.module

import com.example.imedical.forgetpassword.resetpassword.data.repository.ResetRepositoryImpl
import com.example.imedical.forgetpassword.resetpassword.domain.repository.IResetRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ResetPasswordModule {
    @Provides
    @Singleton
    fun provideResetRepository(resetRepositoryImpl: ResetRepositoryImpl)
            : IResetRepository = resetRepositoryImpl
}