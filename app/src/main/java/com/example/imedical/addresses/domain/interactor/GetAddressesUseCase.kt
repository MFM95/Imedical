package com.example.imedical.addresses.domain.interactor

import com.example.imedical.addresses.domain.model.AddressModel
import com.example.imedical.addresses.domain.repository.IAddressesRepository
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import javax.inject.Inject

class GetAddressesUseCase @Inject constructor(private val repository: IAddressesRepository)
    : BaseUseCase<DataWrapper<List<AddressModel>>, Unit>() {
    override suspend fun run(params: Unit): DataWrapper<List<AddressModel>> {
        return repository.getAddresses()
    }
}