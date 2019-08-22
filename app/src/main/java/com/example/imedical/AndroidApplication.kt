package com.example.imedical

import android.app.Application
import com.example.imedical.core.di.component.ApplicationComponent
import com.example.imedical.core.di.component.DaggerApplicationComponent
import com.example.imedical.core.di.module.ApplicationModule
import com.example.imedical.core.di.module.LoginModule
import com.example.imedical.core.di.module.RegistrationModule
import com.example.imedical.core.di.module.VerificationModule

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
class AndroidApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .loginModule(LoginModule())
            .registrationModule(RegistrationModule())
            .verificationModule(VerificationModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }


}