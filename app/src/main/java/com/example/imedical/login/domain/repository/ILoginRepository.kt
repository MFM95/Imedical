package com.example.imedical.login.domain.repository

import com.example.imedical.core.model.DataWrapper

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
interface ILoginRepository {
    suspend fun login(user: String, password: String) : DataWrapper<String>
}