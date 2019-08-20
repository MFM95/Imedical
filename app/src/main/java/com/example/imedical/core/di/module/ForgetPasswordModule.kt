package com.example.imedical.core.di.module

import com.example.imedical.forgetpassword.forget.data.repository.ForgetPasswordRepoImpl
import com.example.imedical.forgetpassword.forget.domain.repository.IForgetPasswordRepo
import com.example.imedical.registration.data.repository.RegistrationRepository
import com.example.imedical.registration.domain.repository.IRegistrationRepository
import com.example.imedical.verification.data.repository.VerificationRepositoryImpl
import com.example.imedical.verification.domain.repository.IVerificationRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ForgetPasswordModule {
    @Provides
    @Singleton
    fun provideForgetRepository(forgetPasswordRepoImpl: ForgetPasswordRepoImpl)
            : IForgetPasswordRepo = forgetPasswordRepoImpl
}