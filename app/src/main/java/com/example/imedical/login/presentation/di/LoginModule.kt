package com.example.imedical.login.presentation.di

import com.example.imedical.core.ServiceGenerator
import com.example.imedical.login.data.repository.LoginRepositoryImpl
import com.example.imedical.login.data.source.remote.LoginAPI
import com.example.imedical.login.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    fun provideLoginAPI(): LoginAPI =
        ServiceGenerator().createService(LoginAPI::class.java, "")

   // todo
}
