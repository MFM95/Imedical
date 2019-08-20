package com.example.imedical.verification.domain.repository

import com.example.imedical.core.model.DataWrapper

interface IVerificationRepository {
    suspend fun verify(verificationCode: String): DataWrapper<String>
    suspend fun resend(mobile: String): DataWrapper<String>
}