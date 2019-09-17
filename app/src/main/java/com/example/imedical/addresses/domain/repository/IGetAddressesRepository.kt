package com.example.imedical.addresses.domain.repository

import com.example.imedical.addresses.domain.model.AddressModel
import com.example.imedical.core.model.DataWrapper

interface IGetAddressesRepository {
    suspend fun getAddresses(): DataWrapper<List<AddressModel>>
}