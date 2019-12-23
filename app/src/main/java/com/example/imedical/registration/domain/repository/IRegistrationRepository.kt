package com.example.imedical.registration.domain.repository

import com.example.imedical.core.model.DataWrapper

/**
 * Created by Ahmed Hassan on 8/14/2019.
 */
interface IRegistrationRepository {
    suspend fun register(name: String, email: String, mobile: String, pass: String, passConf: String)
    : DataWrapper<String>
}