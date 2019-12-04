package com.example.imedical.forgetpassword.resetpassword.data.repository

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.forgetpassword.resetpassword.data.api.ApiCalls
import com.example.imedical.forgetpassword.resetpassword.data.entity.ResetBody
import com.example.imedical.forgetpassword.resetpassword.domain.repository.IResetRepository
import javax.inject.Inject

class ResetRepositoryImpl @Inject constructor(private val apiCalls: ApiCalls): IResetRepository {
    override suspend fun reset(mobile: String, code: String,
                               password: String, passwordConfirmation: String): DataWrapper<String> {
        return DataMapper.mapResetResponse(
            apiCalls.reset(ResetBody(mobile, code, password, passwordConfirmation)))
    }

}