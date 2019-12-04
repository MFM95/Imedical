package com.example.imedical.login.domain.usecase

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import com.example.imedical.login.domain.repository.ILoginRepository
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
class LoginUseCase @Inject constructor(private val iLoginRepository: ILoginRepository) :
    BaseUseCase<DataWrapper<String>, LoginUseCase.LoginParams>() {

    override suspend fun run(params: LoginParams): DataWrapper<String> =
        iLoginRepository.login(params.user, params.password)


    data class LoginParams(val user: String, val password: String)
}