package com.example.imedical.forgetpassword.resetpassword.domain.repository

import com.example.imedical.core.model.DataWrapper

interface IResetRepository  {
    suspend fun reset(mobile: String, code: String,
                      password: String, passwordConfirmation: String)
            : DataWrapper<String>
}