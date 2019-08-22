package com.example.imedical.forgetpassword.forget.domain.usecase

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import com.example.imedical.forgetpassword.forget.domain.repository.IForgetPasswordRepo
import javax.inject.Inject

class ForgetPasswordUseCase @Inject constructor(private val iForgetPasswordRepo: IForgetPasswordRepo)
    : BaseUseCase<DataWrapper<String>, ForgetPasswordUseCase.ForgetPasswordParams>() {
    override suspend fun run(params: ForgetPasswordParams): DataWrapper<String> {
        return iForgetPasswordRepo.forget(params.mobile)
    }


    data class ForgetPasswordParams(val mobile: String)
}