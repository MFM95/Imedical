package com.example.imedical.forgetpassword.forget.domain.repository

import com.example.imedical.core.model.DataWrapper

interface IForgetPasswordRepo {
    suspend fun forget(mobile: String): DataWrapper<String>

}