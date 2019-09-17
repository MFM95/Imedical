package com.example.imedical.addresses.data.repository

import com.example.imedical.addresses.data.api.APICalls
import com.example.imedical.addresses.data.entity.GetAddressesBody
import com.example.imedical.addresses.domain.model.AddressModel
import com.example.imedical.addresses.domain.repository.IGetAddressesRepository
import com.example.imedical.core.model.DataWrapper
import javax.inject.Inject

class GetAddressesRespositoyImpl @Inject constructor(private val apiCalls: APICalls)
    : IGetAddressesRepository {
    override suspend fun getAddresses(): DataWrapper<List<AddressModel>> {
       return DataMapper.mapAddressesResponse(
           apiCalls.getAddresses(GetAddressesBody()))
    }
}