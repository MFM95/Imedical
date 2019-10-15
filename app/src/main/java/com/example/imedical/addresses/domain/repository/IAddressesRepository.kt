package com.example.imedical.addresses.domain.repository

import com.example.imedical.addresses.domain.model.AddressModel
import com.example.imedical.addresses.domain.model.CountryProvincesModel
import com.example.imedical.core.model.DataWrapper

interface IAddressesRepository {
    suspend fun getAddresses(): DataWrapper<List<AddressModel>>
    suspend fun createAddress(alias: String?, address1: String?, address2: String?,
                              countryId: String?, provinceId: String?, phone: String?)
            : DataWrapper<AddressModel>
    suspend fun getCountryProvinces(countryId: String?): DataWrapper<CountryProvincesModel>

    suspend fun updateAddress(
        id: String, alias: String?, address1: String?, address2: String?,
                 countryId: String?, provinceId: String?, phone: String?)
            : DataWrapper<String>

    suspend fun deleteAddress(id: String)
            : DataWrapper<String>

}