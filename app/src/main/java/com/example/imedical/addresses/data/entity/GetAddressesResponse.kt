package com.example.imedical.addresses.data.entity

import com.google.gson.annotations.SerializedName

data class GetAddressesResponse (
    @SerializedName("addresses")
    val addresses: List<AddressResponse>
)