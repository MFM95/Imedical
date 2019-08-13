package com.example.imedical.login.data.repository

import com.example.imedical.login.data.api.ApiCalls
import com.example.imedical.login.data.entity.Credentials
import com.example.imedical.login.domain.model.DataWrapper
import com.example.imedical.login.domain.repository.ILoginRepository

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
class LoginRepository(private val apiCalls: ApiCalls) : ILoginRepository{

    override suspend fun login(user: String, password: String) : DataWrapper<String>{
        //TODO Check for response status and handle caching
        return DataMapper.mapLoginData(apiCalls.login(Credentials(user, password)).body()!!)
    }
}