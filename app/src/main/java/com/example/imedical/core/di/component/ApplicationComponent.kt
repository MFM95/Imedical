package com.example.imedical.core.di.component

import com.example.imedical.AndroidApplication
import com.example.imedical.core.di.module.ApplicationModule
import com.example.imedical.login.presentation.view.activity.LoginActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: AndroidApplication)
    fun inject(loginActivity: LoginActivity)

}