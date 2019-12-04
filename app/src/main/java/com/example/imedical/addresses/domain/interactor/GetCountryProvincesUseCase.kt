package com.example.imedical.addresses.domain.interactor

import com.example.imedical.addresses.domain.model.CountryProvincesModel
import com.example.imedical.addresses.domain.repository.IAddressesRepository
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import javax.inject.Inject

class GetCountryProvincesUseCase @Inject constructor(private val repository: IAddressesRepository)
    : BaseUseCase<DataWrapper<CountryProvincesModel>, Int>() {
    override suspend fun run(params: Int): DataWrapper<CountryProvincesModel> {
        return repository.getCountryProvinces(params)
    }
}