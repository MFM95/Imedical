package com.example.imedical.addresses.domain.interactor

import com.example.imedical.addresses.domain.model.AddressModel
import com.example.imedical.addresses.domain.model.CountryProvincesModel
import com.example.imedical.addresses.domain.repository.IAddressesRepository
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import javax.inject.Inject

class GetCountryProvincesUseCase @Inject constructor(private val repository: IAddressesRepository)
    : BaseUseCase<DataWrapper<CountryProvincesModel>, String>() {
    override suspend fun run(params: String): DataWrapper<CountryProvincesModel> {
        return repository.getCountryProvinces(params)
    }
}