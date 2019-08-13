package com.example.imedical.login.data.api

import com.example.imedical.login.data.entity.Credentials
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
class ApiCalls @Inject constructor(private val retrofit: Retrofit){

    private val iMedicalApi = retrofit.create(IMedicalApi::class.java)

    suspend fun login(credentials: Credentials) = iMedicalApi.login(credentials)
}