package com.example.imedical.core.di.module

import com.example.imedical.login.data.repository.LoginRepository
import com.example.imedical.login.domain.repository.ILoginRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Ahmed Hassan on 8/14/2019.
 */
@Module
class LoginModule {

    @Provides
    @Singleton
    fun provideLoginRepository(loginRepository: LoginRepository) : ILoginRepository =
        loginRepository
}