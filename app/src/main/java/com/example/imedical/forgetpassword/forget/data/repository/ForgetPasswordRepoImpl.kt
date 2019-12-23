package com.example.imedical.forgetpassword.forget.data.repository

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.forgetpassword.forget.data.api.ApiCalls
import com.example.imedical.forgetpassword.forget.data.entity.ForgetPasswordBody
import com.example.imedical.forgetpassword.forget.domain.repository.IForgetPasswordRepo
import javax.inject.Inject

class ForgetPasswordRepoImpl @Inject constructor(private val apiCalls: ApiCalls)
    : IForgetPasswordRepo {
    override suspend fun forget(mobile: String): DataWrapper<String> {
      return DataMapper.mapForgetPasswordResponse(apiCalls.forget(ForgetPasswordBody(mobile)))

    }
}