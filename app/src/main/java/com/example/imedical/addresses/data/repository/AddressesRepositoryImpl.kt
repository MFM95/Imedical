package com.example.imedical.addresses.data.repository

import com.example.imedical.addresses.data.api.APICalls
import com.example.imedical.addresses.data.entity.CreateAddressBody
import com.example.imedical.addresses.data.entity.UpdateAddressResponse
import com.example.imedical.addresses.domain.model.AddressModel
import com.example.imedical.addresses.domain.model.CountryProvincesModel
import com.example.imedical.addresses.domain.repository.IAddressesRepository
import com.example.imedical.core.model.DataWrapper
import javax.inject.Inject

class AddressesRepositoryImpl @Inject constructor(private val apiCalls: APICalls)
    : IAddressesRepository {

    override suspend fun getCountryProvinces(countryId: String?): DataWrapper<CountryProvincesModel> {
       return DataMapper.mapCountryProvincesResponse(
           apiCalls.getCountryProvinces(countryId))
    }

    override suspend fun createAddress(
        alias: String?,
        address1: String?,
        address2: String?,
        countryId: String?,
        provinceId: String?,
        phone: String?
    ): DataWrapper<AddressModel> {
        return DataMapper.mapAddressResponse(
            apiCalls.createAddress(CreateAddressBody(alias, address1, address2,
                countryId, provinceId, phone)))
    }


    override suspend fun getAddresses(): DataWrapper<List<AddressModel>> {
       return DataMapper.mapAddressesResponse(
           apiCalls.getAddresses())
    }

    override suspend fun updateAddress(
        id: String,
        alias: String?,
        address1: String?,
        address2: String?,
        countryId: String?,
        provinceId: String?,
        phone: String?
    ): DataWrapper<String> {
        return DataMapper.mapUpdateAddressResponse(
            apiCalls.updateAddress(
                id, CreateAddressBody(
                    alias, address1, address2, countryId, provinceId, phone)
            ))
    }

    override suspend fun deleteAddress(id: String): DataWrapper<String> {
        return DataMapper.mapDeleteAddressResponse(apiCalls.deleteAddress(id))

    }

}