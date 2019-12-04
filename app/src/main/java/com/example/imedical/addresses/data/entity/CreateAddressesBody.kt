package com.example.imedical.addresses.data.entity

import com.google.gson.annotations.SerializedName

data class CreateAddressBody (
    @SerializedName("alias")
    val alias: String? = "",
    @SerializedName("address_1")
    val address_1: String? = "",
    @SerializedName("address_2")
    val address_2: String? = "",
    @SerializedName("country_id")
    val country_id: Int? = 1,
    @SerializedName("province_id")
    val province_id: Int? = 1,
    @SerializedName("phone")
    val phone: String? = ""
)