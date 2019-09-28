package com.example.imedical.addresses.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.addresses.domain.interactor.CreateAddressUseCase
import com.example.imedical.addresses.domain.interactor.GetAddressesUseCase
import com.example.imedical.addresses.domain.interactor.GetCountryProvincesUseCase
import com.example.imedical.addresses.domain.model.AddressModel
import com.example.imedical.addresses.domain.model.CountryProvincesModel
import com.example.imedical.core.model.DataWrapper
import javax.inject.Inject

class AddressesViewModel @Inject constructor(private val getAddressesUseCase: GetAddressesUseCase,
                                             private val createAddressUseCase: CreateAddressUseCase,
                                             private val getCountryProvincesUseCase: GetCountryProvincesUseCase)
    : ViewModel(){

    private val addressesLiveData: MutableLiveData<List<AddressModel>> = MutableLiveData()
    private val createAddressLiveData: MutableLiveData<AddressModel> = MutableLiveData()
    private val countryProvincesLiveData: MutableLiveData<CountryProvincesModel> = MutableLiveData()

    fun getAddressesLiveData(): MutableLiveData<List<AddressModel>> {
        return addressesLiveData
    }

    fun getAddresses() {
        getAddressesUseCase.execute(Unit, onResult = this::onGetAddressesResult)
    }

    private fun onGetAddressesResult(dataWrapper: DataWrapper<List<AddressModel>>) {
        addressesLiveData.value = dataWrapper.data
    }


    fun getCreatedAddressLiveData(): MutableLiveData<AddressModel> {
        return createAddressLiveData
    }

    fun createAddress(alias: String?, address1: String, address2: String,
                      countryId: String, provinceId: String, phone: String) {
        createAddressUseCase.execute(CreateAddressUseCase.CreateAddressParams(
            alias, address1, address2, countryId, provinceId, phone)
            , onResult = this::onCreateAddressResult)
    }

    private fun onCreateAddressResult(dataWrapper: DataWrapper<AddressModel>) {
        createAddressLiveData.value = dataWrapper.data
    }


    fun getCountryProvincesLiveData(): MutableLiveData<CountryProvincesModel> {
        return countryProvincesLiveData
    }

    fun getCountryProvinces(countryId: String) {
        getCountryProvincesUseCase.execute(countryId,
            onResult = this::onGetCountryProvincesResult)
    }

    private fun onGetCountryProvincesResult(dataWrapper: DataWrapper<CountryProvincesModel>) {
        countryProvincesLiveData.value = dataWrapper.data
    }
}