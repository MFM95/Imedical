package com.example.imedical.forgetpassword.resetpassword.domain.repository

import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.forgetpassword.resetpassword.data.entity.ResetBody
import com.example.imedical.forgetpassword.resetpassword.data.entity.ResetResponse

interface IResetRepository  {
    suspend fun reset(mobile: String, code: String,
                      password: String, passwordConfirmation: String)
            : DataWrapper<String>
}