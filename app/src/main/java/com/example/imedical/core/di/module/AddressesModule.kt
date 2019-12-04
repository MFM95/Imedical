package com.example.imedical.core.di.module

import com.example.imedical.addresses.data.repository.AddressesRepositoryImpl
import com.example.imedical.addresses.domain.repository.IAddressesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AddressesModule {
    @Provides
    @Singleton
    fun provideAddressesRepository(addressesRepositoryImpl: AddressesRepositoryImpl)
            : IAddressesRepository =
        addressesRepositoryImpl
}