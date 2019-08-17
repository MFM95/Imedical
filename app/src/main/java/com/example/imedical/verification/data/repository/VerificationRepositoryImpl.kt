package com.example.imedical.verification.data.repository

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.verification.data.api.ApiCalls
import com.example.imedical.verification.data.entity.VerificationBody
import com.example.imedical.verification.domain.repository.IVerificationRepository
import javax.inject.Inject

class VerificationRepositoryImpl @Inject constructor(private val apiCalls: ApiCalls) : IVerificationRepository {
    override suspend fun verify(verificationCode: String): DataWrapper<String> {

        return DataMapper
            .mapVerificationResponse(apiCalls.verify(VerificationBody(verificationCode)))

    }
}