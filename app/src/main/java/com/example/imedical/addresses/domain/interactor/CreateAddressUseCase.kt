package com.example.imedical.addresses.domain.interactor

import com.example.imedical.addresses.domain.model.AddressModel
import com.example.imedical.addresses.domain.repository.IAddressesRepository
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import javax.inject.Inject

class CreateAddressUseCase @Inject constructor(private val repository: IAddressesRepository)
    : BaseUseCase<DataWrapper<AddressModel>, CreateAddressUseCase.CreateAddressParams>() {
    override suspend fun run(params: CreateAddressParams): DataWrapper<AddressModel> {
        return repository.createAddress(params.alias, params.address_1, params.address_2,
            params.country_id, params.province_id, params.phone)
    }

    data class CreateAddressParams(
        val alias: String? = "",
        val address_1: String? = "",
        val address_2: String? = "",
        val country_id: String? = "",
        val province_id: String? = "",
        val phone: String? = ""
    )
}