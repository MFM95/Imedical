package com.example.imedical.addresses.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.addresses.domain.interactor.*
import com.example.imedical.addresses.domain.model.AddressModel
import com.example.imedical.addresses.domain.model.CountryProvincesModel
import com.example.imedical.core.model.DataWrapper
import com.example.imedical.home.domain.usecase.CheckoutUseCase
import javax.inject.Inject

class AddressesViewModel @Inject constructor(private val getAddressesUseCase: GetAddressesUseCase,
                                             private val createAddressUseCase: CreateAddressUseCase,
                                             private val getCountryProvincesUseCase: GetCountryProvincesUseCase,
                                             private val updateAddressUseCase: UpdateAddressUseCase,
                                             private val deleteAddressUseCase: DeleteAddressUseCase,
                                             private val checkoutUseCase: CheckoutUseCase)
    : ViewModel(){

    private val addressesLiveData: MutableLiveData<List<AddressModel>> = MutableLiveData()
    private val createAddressLiveData: MutableLiveData<AddressModel> = MutableLiveData()
    private val countryProvincesLiveData: MutableLiveData<CountryProvincesModel> = MutableLiveData()
    private val updateAddressLiveData: MutableLiveData<String> = MutableLiveData()
    private val deleteAddressLiveData: MutableLiveData<String> = MutableLiveData()
    val checkoutLiveData by lazy { MutableLiveData<DataWrapper<Unit>>() }

    fun checkout(id: Int){
        checkoutUseCase.execute(id, this::checkoutResult)
    }

    private fun checkoutResult(r: DataWrapper<Unit>){
        checkoutLiveData.value = r
    }
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
                      countryId: Int, provinceId: Int, phone: String) {
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

    fun getCountryProvinces(countryId: Int) {
        getCountryProvincesUseCase.execute(countryId,
            onResult = this::onGetCountryProvincesResult)
    }

    private fun onGetCountryProvincesResult(dataWrapper: DataWrapper<CountryProvincesModel>) {
        countryProvincesLiveData.value = dataWrapper.data
    }

    fun getUpdateAddressLiveData(): MutableLiveData<String> {
        return updateAddressLiveData
    }

    fun updateAddress(id: Int, alias: String?, address1: String?, address2: String?,
                      countryId: Int?, provinceId: Int?, phone: String?) {
        updateAddressUseCase.execute(UpdateAddressUseCase.UpdateAddressParams(
            id, alias, address1, address2, countryId, provinceId, phone)
            , onResult = this::onUpdateAddressResult)
    }

    private fun onUpdateAddressResult(dataWrapper: DataWrapper<String>) {
        updateAddressLiveData.value = dataWrapper.data
    }

    fun getDeleteAddressLiveData(): MutableLiveData<String> {
        return deleteAddressLiveData
    }

    fun deleteAddress(id: Int) {
        deleteAddressUseCase.execute(
            DeleteAddressUseCase.DeleteAddressParams(id)
            , onResult = this::onDeleteAddressResult)
    }

    private fun onDeleteAddressResult(dataWrapper: DataWrapper<String>) {
        deleteAddressLiveData.value = dataWrapper.data
    }

}