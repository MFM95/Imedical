package com.example.imedical.addresses.data.repository

import com.example.imedical.addresses.data.entity.AddressResponse
import com.example.imedical.addresses.data.entity.GetAddressesResponse
import com.example.imedical.addresses.domain.model.AddressModel
import com.example.imedical.addresses.domain.model.Country
import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.model.DataWrapper

object DataMapper {
    fun mapAddressesResponse(apiResponse: ApiResponse<GetAddressesResponse>)
            : DataWrapper<List<AddressModel>> {

        if (!apiResponse.status || apiResponse.data == null)
            return DataWrapper(apiResponse.status, null, apiResponse.error)


        val result = ArrayList<AddressModel>()

        for (address in apiResponse.data.addresses) {
            result.add(mapAddress(address))
        }

        return DataWrapper(apiResponse.status, result, apiResponse.error)
    }

    private fun mapAddress(addressResponse: AddressResponse): AddressModel {
        return AddressModel(
            addressResponse.alias,
            addressResponse.address_1,
            addressResponse.address_2,
            addressResponse.phone,
            Country(addressResponse.country?.id,
                addressResponse.country?.name) ,
            addressResponse.province
        )
    }
}