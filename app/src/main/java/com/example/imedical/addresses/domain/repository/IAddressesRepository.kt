package com.example.imedical.addresses.domain.repository

import com.example.imedical.addresses.domain.model.AddressModel
import com.example.imedical.addresses.domain.model.CountryProvincesModel
import com.example.imedical.core.model.DataWrapper

interface IAddressesRepository {
    suspend fun getAddresses(): DataWrapper<List<AddressModel>>
    suspend fun createAddress(alias: String?, address1: String?, address2: String?,
                              countryId: Int?, provinceId: Int?, phone: String?)
            : DataWrapper<AddressModel>
    suspend fun getCountryProvinces(countryId: Int?): DataWrapper<CountryProvincesModel>

    suspend fun updateAddress(
        id: Int, alias: String?, address1: String?, address2: String?,
                 countryId: Int?, provinceId: Int?, phone: String?)
            : DataWrapper<String>

    suspend fun deleteAddress(id: Int)
            : DataWrapper<String>

}