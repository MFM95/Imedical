package com.example.imedical.addresses.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.addresses.domain.interactor.GetAddressesUseCase
import com.example.imedical.addresses.domain.model.AddressModel
import com.example.imedical.core.model.DataWrapper
import javax.inject.Inject

class AddressesViewModel @Inject constructor(private val getAddressesUseCase: GetAddressesUseCase)
    : ViewModel(){

    private val addressesLiveData: MutableLiveData<List<AddressModel>> = MutableLiveData()

    fun getAddressesLiveData(): MutableLiveData<List<AddressModel>> {
        return addressesLiveData
    }

    fun getAddresses() {
        getAddressesUseCase.execute(Unit, onResult = this::onResult)
    }

    private fun onResult(dataWrapper: DataWrapper<List<AddressModel>>) {
        addressesLiveData.value = dataWrapper.data
    }
}