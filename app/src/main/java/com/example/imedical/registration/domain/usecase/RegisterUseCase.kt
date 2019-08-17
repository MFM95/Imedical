package com.example.imedical.registration.domain.usecase

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import com.example.imedical.registration.domain.repository.IRegistrationRepository
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 8/14/2019.
 */
class RegisterUseCase @Inject constructor(private val registrationRepository: IRegistrationRepository)
    : BaseUseCase<DataWrapper<String>, RegisterUseCase.RegistrationParams>() {

    override suspend fun run(params: RegistrationParams): DataWrapper<String> {
        return registrationRepository.register(params.name, params.email,
            params.mobile, params.password, params.passwordConf)

    }

    data class RegistrationParams(val name: String,
                                  val email: String,
                                  val mobile: String,
                                  val password: String,
                                  val passwordConf: String)
}