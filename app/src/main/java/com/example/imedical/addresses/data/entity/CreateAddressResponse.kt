package com.example.imedical.addresses.data.entity

import com.google.gson.annotations.SerializedName

data class CreateAddressResponse (
    @SerializedName("message")
    val message: String,
    @SerializedName("address")
    val address: AddressResponse
)