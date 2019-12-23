package com.example.imedical.forgetpassword.resetpassword.domain.usecase

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import com.example.imedical.forgetpassword.resetpassword.data.repository.ResetRepositoryImpl
import javax.inject.Inject

class ResetUseCase @Inject constructor(private val resetRepositoryImpl: ResetRepositoryImpl) :
    BaseUseCase<DataWrapper<String>, ResetUseCase.ResetParams>() {
    override suspend fun run(params: ResetParams): DataWrapper<String> {
        return resetRepositoryImpl.reset(params.mobile, params.code,
            params.password, params.passwordConfirmation)
    }

    data class ResetParams(
            val mobile: String,
            val code: String,
            val password: String,
            val passwordConfirmation: String
        )

}