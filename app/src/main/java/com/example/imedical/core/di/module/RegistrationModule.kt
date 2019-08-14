package com.example.imedical.core.di.module

import com.example.imedical.registration.data.repository.RegistrationRepository
import com.example.imedical.registration.domain.repository.IRegistrationRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Ahmed Hassan on 8/14/2019.
 */
@Module
class RegistrationModule {
    @Provides
    @Singleton
    fun provideLoginRepository(registrationRepository: RegistrationRepository) : IRegistrationRepository =
        registrationRepository
}