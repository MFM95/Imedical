package com.example.imedical.registration.data.repository

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.registration.data.api.ApiCalls
import com.example.imedical.registration.data.entity.RegistrationForm
import com.example.imedical.registration.domain.repository.IRegistrationRepository
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 8/14/2019.
 */
class RegistrationRepository @Inject constructor(private val apiCalls: ApiCalls) : IRegistrationRepository {
    override suspend fun register(
        name: String,
        email: String,
        mobile: String,
        pass: String,
        passConf: String
    ): DataWrapper<String> {

        return DataMapper.mapRegistrationResponse(
            apiCalls.register(
                RegistrationForm(
                    name, email, mobile, pass, passConf
                )
            )
        )
    }
}