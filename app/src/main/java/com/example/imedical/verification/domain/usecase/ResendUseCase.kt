package com.example.imedical.verification.domain.usecase

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import com.example.imedical.verification.domain.repository.IVerificationRepository
import javax.inject.Inject

class ResendUseCase @Inject constructor(private val iVerificationRepository: IVerificationRepository)
    : BaseUseCase<DataWrapper<String>, ResendUseCase.ResendingParams>() {
    override suspend fun run(params: ResendingParams): DataWrapper<String> {
        return iVerificationRepository.resend(params.mobile)
    }


   data class ResendingParams(val mobile: String)

}