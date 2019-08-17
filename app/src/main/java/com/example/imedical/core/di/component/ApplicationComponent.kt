package com.example.imedical.core.di.component

import com.example.imedical.AndroidApplication
import com.example.imedical.core.di.module.ApplicationModule
import com.example.imedical.core.di.module.LoginModule
import com.example.imedical.core.di.module.RegistrationModule
import com.example.imedical.login.presentation.view.activity.LoginActivity
import com.example.imedical.registration.presentation.fragment.RegistrationFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
@Singleton
@Component(modules = [ApplicationModule::class, LoginModule::class, RegistrationModule::class])
interface ApplicationComponent {

    fun inject(application: AndroidApplication)
    fun inject(loginActivity: LoginActivity)
    fun inject(registrationFragment: RegistrationFragment)

}