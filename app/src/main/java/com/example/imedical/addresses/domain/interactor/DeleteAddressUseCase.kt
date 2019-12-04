package com.example.imedical.addresses.domain.interactor

import com.example.imedical.addresses.domain.model.AddressModel
import com.example.imedical.addresses.domain.repository.IAddressesRepository
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import javax.inject.Inject

class DeleteAddressUseCase @Inject constructor(
    private val repository: IAddressesRepository)
    : BaseUseCase<DataWrapper<String>, DeleteAddressUseCase.DeleteAddressParams>() {
    override suspend fun run(params: DeleteAddressParams)
            : DataWrapper<String> {
        return repository.deleteAddress(params.id)
    }

    data class DeleteAddressParams(
        val id: Int = 0
    )
}