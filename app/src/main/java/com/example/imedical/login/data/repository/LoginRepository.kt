package com.example.imedical.login.data.repository

import com.example.imedical.login.data.api.ApiCalls
import com.example.imedical.login.data.entity.Credentials
import com.example.imedical.login.domain.model.DataWrapper
import com.example.imedical.login.domain.repository.ILoginRepository
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
class LoginRepository @Inject constructor(private val apiCalls: ApiCalls) : ILoginRepository{

    override suspend fun login(user: String, password: String) : DataWrapper<String>{
        val error = Validator.validateLogin(user, password)

        if(error.isNotEmpty())
            return DataWrapper(false, null, error)

        return DataMapper.mapLoginData(apiCalls.login(Credentials(user, password)).body()!!)
    }
}