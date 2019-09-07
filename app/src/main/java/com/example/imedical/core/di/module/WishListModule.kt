package com.example.imedical.core.di.module

import com.example.imedical.wishlist.data.repository.WishedRepository
import com.example.imedical.wishlist.domain.repository.IWishedRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Ahmed Hassan on 9/7/2019.
 */

@Module
class WishListModule {
    @Provides
    @Singleton
    fun provideWishedRepository(wishedRepository: WishedRepository) :IWishedRepository{
        return wishedRepository
    }
}