package com.example.imedical.addresses.domain.interactor

import com.example.imedical.addresses.domain.model.AddressModel
import com.example.imedical.addresses.domain.repository.IAddressesRepository
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import javax.inject.Inject

class UpdateAddressUseCase @Inject constructor(
    private val repository: IAddressesRepository)
    : BaseUseCase<DataWrapper<String>, UpdateAddressUseCase.UpdateAddressParams>() {
    override suspend fun run(params: UpdateAddressParams)
            : DataWrapper<String> {
        return repository.updateAddress(params.id, params.alias, params.address_1, params.address_2,
            params.country_id, params.province_id, params.phone)
    }

    data class UpdateAddressParams(
        val id: Int = 0,
        val alias: String? = "",
        val address_1: String? = "",
        val address_2: String? = "",
        val country_id: Int? = 1,
        val province_id: Int? = 1,
        val phone: String? = ""
    )
}