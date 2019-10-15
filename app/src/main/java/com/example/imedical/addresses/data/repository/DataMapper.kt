package com.example.imedical.addresses.data.repository

import com.example.imedical.addresses.data.entity.*
import com.example.imedical.addresses.domain.model.AddressModel
import com.example.imedical.addresses.domain.model.Country
import com.example.imedical.addresses.domain.model.CountryProvincesModel
import com.example.imedical.addresses.domain.model.Province
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
            addressResponse.id,
            addressResponse.alias,
            addressResponse.address_1,
            addressResponse.address_2,
            addressResponse.phone,
            Country(addressResponse.country?.id,
                addressResponse.country?.name) ,
            Province(addressResponse.province?.id,
                addressResponse.province?.name)
        )
    }

    fun mapAddressResponse(apiResponse: ApiResponse<CreateAddressResponse>)
            : DataWrapper<AddressModel> {

        if (!apiResponse.status || apiResponse.data == null)
            return DataWrapper(apiResponse.status, null, apiResponse.error)

        val result = mapAddress(apiResponse.data.address)

        return DataWrapper(apiResponse.status, result, apiResponse.error)
    }

    fun mapCountryProvincesResponse(apiResponse: ApiResponse<CountryProvincesResponse>)
            : DataWrapper<CountryProvincesModel> {

        if (!apiResponse.status || apiResponse.data == null)
            return DataWrapper(apiResponse.status, null, apiResponse.error)

        val response = apiResponse.data
        val provinces = ArrayList<Province>()
        for (province in response.provinces) {
            provinces.add(Province(province.id, province.name))
        }
        val result  = CountryProvincesModel(response.id, response.name, provinces)

        return DataWrapper(apiResponse.status, result, apiResponse.error)
    }

    fun mapUpdateAddressResponse(apiResponse: ApiResponse<UpdateAddressResponse>)
            : DataWrapper<String> {

        if (!apiResponse.status || apiResponse.data == null)
            return DataWrapper(apiResponse.status, null, apiResponse.error)

        val result = apiResponse.data.message

        return DataWrapper(apiResponse.status, result, apiResponse.error)
    }

    fun mapDeleteAddressResponse(apiResponse: ApiResponse<DeleteAddressResponse>)
            : DataWrapper<String> {

        if (!apiResponse.status || apiResponse.data == null)
            return DataWrapper(apiResponse.status, null, apiResponse.error)

        val result = apiResponse.data.message

        return DataWrapper(apiResponse.status, result, apiResponse.error)
    }

}