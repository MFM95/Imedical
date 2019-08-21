package com.example.imedical.core.di.component

import com.example.imedical.AndroidApplication
import com.example.imedical.core.di.module.*
import com.example.imedical.forgetpassword.forget.presentation.activity.ForgetPasswordActivity
import com.example.imedical.forgetpassword.verify.presentation.activity.VerifyPasswordActivity
import com.example.imedical.login.presentation.view.activity.LoginActivity
import com.example.imedical.registration.presentation.fragment.RegistrationFragment
import com.example.imedical.verification.presentation.fragment.VerificationFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
@Singleton
@Component(modules = [ApplicationModule::class,
    LoginModule::class,
    RegistrationModule::class,
    VerificationModule::class,
    ForgetPasswordModule::class])

interface ApplicationComponent {

    fun inject(application: AndroidApplication)
    fun inject(loginActivity: LoginActivity)
    fun inject(registrationFragment: RegistrationFragment)
    fun inject(verificationFragment: VerificationFragment)
    fun inject(forgetPasswordActivity: ForgetPasswordActivity)
    fun inject(verifyPasswordActivity: VerifyPasswordActivity)

}