package com.example.imedical.verification.domain.usecase

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import com.example.imedical.verification.domain.repository.IVerificationRepository
import javax.inject.Inject

class VerifyUseCase @Inject constructor(private val iVerificationRepository: IVerificationRepository)
    : BaseUseCase<DataWrapper<String>, VerifyUseCase.VerificationParams>() {
    override suspend fun run(params: VerificationParams): DataWrapper<String> {
        return iVerificationRepository.verify(params.code)
    }


    data class VerificationParams(val code: String)

}