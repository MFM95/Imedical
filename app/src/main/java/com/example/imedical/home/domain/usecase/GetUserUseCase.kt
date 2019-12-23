package com.example.imedical.home.domain.usecase

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import com.example.imedical.home.domain.repository.IProductRepository
import com.example.imedical.login.domain.model.UserModel
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 8/19/2019.
 */
class GetUserUseCase @Inject constructor(private val iProductRepository: IProductRepository) :
    BaseUseCase<DataWrapper<UserModel>, String>() {
    override suspend fun run(params: String): DataWrapper<UserModel> {
        return iProductRepository.getAuthUser(params)
    }

}